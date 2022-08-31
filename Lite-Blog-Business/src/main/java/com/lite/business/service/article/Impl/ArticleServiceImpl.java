package com.lite.business.service.article.Impl;

import com.lite.business.entity.article.Article;
import com.lite.business.dao.article.ArticleMapper;
import com.lite.business.service.article.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
