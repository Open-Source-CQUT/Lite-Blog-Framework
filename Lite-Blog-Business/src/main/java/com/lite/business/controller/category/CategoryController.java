package com.lite.business.controller.category;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lite.business.service.category.ICategoryService;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章分类信息表 前端控制器
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
       @Autowired
       ICategoryService categoryService;
}
