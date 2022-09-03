package com.lite.business.service.article.Impl;

import com.lite.business.entity.article.ArticleUser;
import com.lite.business.dao.articleuser.ArticleUserMapper;
import com.lite.business.service.article.IArticleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 文章-用户关系表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-09-03
 */
@Service
public class ArticleUserServiceImpl extends ServiceImpl<ArticleUserMapper, ArticleUser> implements IArticleUserService {


        @Autowired
        ArticleUserMapper articleuserMapper;

}
