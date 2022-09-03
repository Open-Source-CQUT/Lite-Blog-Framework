package com.lite.business.service.article.Impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.auth.vo.UserTokenVo;
import com.lite.business.convert.article.ArticleConvert;
import com.lite.business.dto.article.ArticleDTO;
import com.lite.business.entity.article.Article;
import com.lite.business.dao.article.ArticleMapper;
import com.lite.business.entity.article.ArticleUser;
import com.lite.business.entity.status.Status;
import com.lite.business.exceptioin.ArticleException;
import com.lite.business.service.article.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lite.business.service.article.IArticleUserService;
import com.lite.business.vo.article.ArticleSimpleVO;
import com.lite.business.vo.article.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleConvert articleConvert;

    @Autowired
    IArticleUserService articleUserService;

    @Autowired
    LiteBlogContextUtils contextUtils;


    @Override
    public Boolean publishArticle(ArticleDTO articleDTO) {

        Article article = articleConvert.dtoToEntity(articleDTO);

        ArticleUser articleUser = new ArticleUser(article.getId(), article.getUId(), Status.PUBLISHED.val());

        return this.updateById(article) &&
                articleUserService.update(articleUser,new LambdaUpdateWrapper<ArticleUser>()
                        .eq(ArticleUser::getArticleId, articleUser.getArticleId())
                        .eq(ArticleUser::getUserId, articleUser.getUserId()));
    }

    @Override
    public ArticleSimpleVO createDraft(ArticleDTO articleDTO) throws ArticleException {

        Article article = articleConvert.dtoToEntity(articleDTO);

        //将新数据插入
        if (!this.save(article))
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文章创建失败");

        //获取当前线程的用户信息
        UserTokenVo localUserInfo = contextUtils.getLocalUserInfo();

        //关系表插入
        articleUserService.save(
                new ArticleUser(article.getId(), localUserInfo.getId(), Status.UN_PUBLISHED.val()));

        //返回信息
        article = articleMapper.getById(article.getId());

        return articleConvert.entityToSimpleVO(article);
    }

    @Override
    public ArticleSimpleVO saveDraft(ArticleDTO articleDTO) throws ArticleException {

        Article article = articleConvert.dtoToEntity(articleDTO);

        if (!this.updateById(article))
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文章保存失败");

        article = articleMapper.getById(article.getId());

        return articleConvert.entityToSimpleVO(article);
    }


    @Override
    public List<ArticleVO> getArticleList(Long statusId, Integer page, Integer size) {

        Assert.state(page-- > 0, "必须是正整数");

        if (Objects.isNull(size))
            return articleConvert.entityListToVoList(articleMapper.selectPage(statusId, page * 10, 10));

        Assert.state(size > 0, "必须是正整数");

        return articleConvert.entityListToVoList(articleMapper.selectPage(statusId, page * size, size));
    }

    @Override
    public ArticleVO getArticleDetail(Long articleId) {
        Assert.state(articleId != null && articleId > 0, "非法的文章ID");

        return articleConvert.entityToVO(articleMapper.getById(articleId));
    }

}
