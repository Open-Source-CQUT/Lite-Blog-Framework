package com.lite.business.controller.label;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lite.business.service.label.ILabelService;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签信息表 前端控制器
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/label")
public class LabelController {
       @Autowired
       ILabelService labelService;
}
