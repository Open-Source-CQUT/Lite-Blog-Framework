package com.lite.business.controller.article;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lite.business.service.article.IArticleService;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客文章信息表 前端控制器
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
       @Autowired
       IArticleService articleService;
}
