package com.lite.business.service.article;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lite.business.dto.article.ArticleDTO;
import com.lite.business.entity.article.Article;
import com.lite.business.exceptioin.ArticleException;
import com.lite.business.vo.article.ArticleQueryVO;
import com.lite.business.vo.article.ArticleUpdateResVO;
import com.lite.business.vo.article.ArticleVO;

import java.util.List;

/**
 * <p>
 * 博客文章信息表 服务类
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
public interface IArticleService extends IService<Article> {


    Boolean publishArticle(ArticleDTO articleDTO) throws ArticleException;

    ArticleUpdateResVO createDraft(ArticleDTO articleDTO) throws ArticleException;

    ArticleUpdateResVO updateDraft(ArticleDTO articleDTO) throws ArticleException;

    ArticleUpdateResVO updateArticle(ArticleDTO articleDTO) throws ArticleException;

    List<ArticleVO> getArticleList(Long statusId,Integer page, Integer size);

    Article getArticleInfo(Long articleId);

    ArticleVO getArticleUpdateInfo(Long articleId);

    ArticleQueryVO getArticleQueryInfo(Long articleId);


}
