package com.lite.api.controller.publics.bussiness.share;


import com.lite.business.convert.share.ShareConvert;
import com.lite.business.service.share.IShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态信息表 前端控制器
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/share")
public class ShareController {
        @Autowired
        IShareService shareService;

        @Autowired
        ShareConvert shareConvert;
}
