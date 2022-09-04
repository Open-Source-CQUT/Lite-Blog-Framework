package com.lite.business.service.article.Impl;

import com.lite.business.dao.article.ArticleDraftMapper;
import com.lite.business.entity.article.ArticleDraft;
import com.lite.business.service.article.IArticleDraftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 文章-草稿关系表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-09-04
 */
@Service
public class ArticleDraftServiceImpl extends ServiceImpl<ArticleDraftMapper, ArticleDraft> implements IArticleDraftService {


        @Autowired
        ArticleDraftMapper articledraftMapper;
}
