package com.lite.api.controller.publics.bussiness.label;


import com.lite.business.convert.label.LabelConvert;
import com.lite.business.service.label.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

        @Autowired
        LabelConvert labelConvert;
}
