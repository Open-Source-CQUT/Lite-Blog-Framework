package com.lite.business.service.category.Impl;

import com.lite.business.entity.category.Category;
import com.lite.business.dao.category.CategoryMapper;
import com.lite.business.service.category.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
