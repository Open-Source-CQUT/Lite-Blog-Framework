package com.lite.business.service.category.Impl;

import com.lite.business.entity.category.Category;
import com.lite.business.dao.category.CategoryMapper;
import com.lite.business.service.category.ICategoryService;
import com.lite.business.convert.category.CategoryConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 文章分类信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


        @Autowired
        CategoryMapper categoryMapper;

        @Autowired
        CategoryConvert categoryConvert;
}
