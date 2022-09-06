package com.lite.business.service.article.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.auth.vo.UserTokenVo;
import com.lite.business.convert.article.ArticleConvert;
import com.lite.business.dto.article.ArticleDTO;
import com.lite.business.entity.article.Article;
import com.lite.business.dao.article.ArticleMapper;
import com.lite.business.entity.article.ArticleDraft;
import com.lite.business.entity.article.ArticleUser;
import com.lite.business.entity.status.Status;
import com.lite.business.exceptioin.ArticleException;
import com.lite.business.service.article.IArticleDraftService;
import com.lite.business.service.article.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lite.business.service.article.IArticleUserService;
import com.lite.business.vo.article.ArticleQueryVO;
import com.lite.business.vo.article.ArticleUpdateResVO;
import com.lite.business.vo.article.ArticleVO;
import com.lite.common.i18n.SystemMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 博客文章信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Service
//设置全局的缓存key生成器

public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleConvert articleConvert;

    @Autowired
    IArticleUserService articleUserService;

    @Autowired
    IArticleDraftService articleDraftService;

    @Autowired
    LiteBlogContextUtils contextUtils;


    /**
     * 发布分为两种情况,一种是初次发布文章,另一种是文章发布后再次修改再发布
     */
    @Override
    @CacheEvict(value = "ArticleInfo", key = "#articleDTO.id")
    @Transactional(rollbackFor = Exception.class)
    public Boolean publishArticle(ArticleDTO articleDTO) throws ArticleException {

        //要发布的草稿内容
        Article draft = articleConvert.dtoToEntity(articleDTO);

        //获取草稿ID
        Long draftId = draft.getId();

        //根据草稿ID找到其文章ID，如果是初次发布的话文章ID就是草稿ID，二次发布的话草稿ID与文章ID则不同
        ArticleDraft articleDraft = articleDraftService
                .getOne(new LambdaQueryWrapper<ArticleDraft>().eq(ArticleDraft::getDraftId, draftId));

        //因为乐观锁的存在需要获取version
        Article article = this.getById(articleDraft.getArticleId());

        //将草稿内容复制进文章中
        article.clone(draft);

        article.setStatusId(Status.REVIEW.val());

        //只有当文章ID与草稿ID不相同时，才有必要去删除草稿
        if (!articleDraft.getDraftId().equals(articleDraft.getArticleId()) && articleUserService.remove(new LambdaQueryWrapper<ArticleUser>().eq(ArticleUser::getArticleId, draftId))) {
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文章发布失败");
        }

        //更新文章内容以及文章状态 并且 文章发布后删除 文章草稿
        return this.updateById(article) &&
                articleDraftService.remove(new LambdaQueryWrapper<ArticleDraft>().eq(ArticleDraft::getDraftId, draftId));

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleUpdateResVO createDraft(ArticleDTO articleDTO) throws ArticleException {

        Article article = articleConvert.dtoToEntity(articleDTO);
        article.setStatusId(Status.UN_PUBLISHED.val());

        //将新数据插入
        if (!this.save(article)) {
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文章创建失败");
        }

        //获取当前线程的用户信息
        UserTokenVo localUserInfo = contextUtils.getLocalUserInfo();

        //文章用户关系表插入
        articleUserService.save(new ArticleUser(article.getId(), localUserInfo.getId()));

        //文章草稿关系表插入 未发布之前的文章,自己就是自己的草稿
        articleDraftService.save(new ArticleDraft(article.getId(), article.getId()));

        //返回信息
        article = articleMapper.getById(article.getId());

        return articleConvert.entityToUpdateResVO(article);
    }

    /**
     * 更新一篇草稿,更新草稿时只需要修改文章表
     */
    @Override
    @CacheEvict(value = "ArticleInfo", key = "#articleDTO.id")
    @Transactional(rollbackFor = Exception.class)
    public ArticleUpdateResVO updateDraft(ArticleDTO articleDTO) throws ArticleException {

        Article article = articleConvert.dtoToEntity(articleDTO);

        if (!this.updateById(article)) {
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.draft.save"));
        }

        article = articleMapper.getById(article.getId());

        return articleConvert.entityToUpdateResVO(article);
    }

    /**
     * 更新一篇文章,更新一篇文章时，为了保存编辑文章时的草稿和不影响原有的数据需要插入一条新的数据来当草稿，
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleUpdateResVO updateArticle(ArticleDTO articleDTO) throws ArticleException {

        //将dto转换为实体类
        Article article = articleConvert.dtoToEntity(articleDTO);

        //新建草稿
        Article draft = new Article(article);

        //将草稿插入文章表中
        if (!this.save(draft)) {
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.draft.create"));
        }

        ArticleDraft articleDraft = new ArticleDraft(article.getId(), draft.getId());

        //将文章-草稿的关系插入关系表中
        if (!articleDraftService.save(articleDraft)) {
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.draft.create"));
        }

        ArticleUser draftUser = new ArticleUser(draft.getId(), contextUtils.getLocalUserInfo().getId());

        //将草稿-用户的关系插入关系表中
        if (!articleUserService.save(draftUser)) {
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.draft.create"));
        }

        //获取插入完成后的草稿
        Article insertedDraft = articleMapper.getById(draft.getId());

        //构造updateResVO
        return articleConvert.entityToUpdateResVO(insertedDraft);
    }


    @Override
    @Cacheable("ArticleList")
    public List<ArticleVO> getArticleList(Long statusId, Integer page, Integer size) {

        Assert.state(page-- > 0, SystemMessages.get("number.positive"));

        if (Objects.isNull(size)) {
            return articleConvert.entityListToVoList(articleMapper.selectPage(statusId, page * 10, 10));
        }

        Assert.state(size > 0, SystemMessages.get("number.positive"));

        return articleConvert.entityListToVoList(articleMapper.selectPage(statusId, page * size, size));
    }

    @Override
    public Article getArticleInfo(Long articleId) {
        Assert.state(articleId != null && articleId > 0, SystemMessages.get("error.article.invalidID"));
        return articleMapper.getById(articleId);
    }

    @Override
    public ArticleVO getArticleUpdateInfo(Long articleId) {
        return articleConvert.entityToVO(getArticleInfo(articleId));
    }

    @Override
    @Cacheable("ArticleInfo")
    public ArticleQueryVO getArticleQueryInfo(Long articleId) {
        return articleConvert.entityToQueryVo(getArticleInfo(articleId));
    }

}
