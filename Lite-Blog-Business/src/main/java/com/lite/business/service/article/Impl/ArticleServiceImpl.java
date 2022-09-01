package com.lite.business.service.article.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lite.business.convert.article.ArticleConvert;
import com.lite.business.dto.article.ArticleDTO;
import com.lite.business.dto.article.ArticleSimpleDto;
import com.lite.business.entity.article.Article;
import com.lite.business.dao.article.ArticleMapper;
import com.lite.business.exceptioin.ArticleException;
import com.lite.business.service.article.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lite.business.vo.article.ArticleSimpleVO;
import com.lite.business.vo.article.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public Boolean publishArticle(ArticleDTO articleDTO) {
        Article article = articleConvert.dtoToEntity(articleDTO);
        article.setPublished(true);
        return this.updateById(article);
    }

    @Override
    public ArticleSimpleVO createDraft(ArticleDTO articleDTO) throws ArticleException {

        Article article = articleConvert.dtoToEntity(articleDTO);

        if (!this.save(article))
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文章创建失败");

        article = this.getById(article.getId());

        return articleConvert.entityToSimpleVO(article);
    }

    @Override
    public ArticleSimpleVO saveDraft(ArticleDTO articleDTO) throws ArticleException {

        Article article = articleConvert.dtoToEntity(articleDTO);

        if (!this.updateById(article))
            throw new ArticleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文章保存失败");

        article = this.getById(article.getId());

        return articleConvert.entityToSimpleVO(article);
    }

    @Override
    public List<ArticleVO> getArticleList(Integer page) {
        return this.getArticleList(page,10);
    }

    @Override
    public List<ArticleVO> getArticleList(Integer page,Integer size) {

        return articleConvert.entityListToVoList(this.page( new Page<>(page,size),
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getPublished, true)
                        .orderBy(true,false,Article::getUpdatedTime)).getRecords());
    }

}
