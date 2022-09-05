package com.lite.api.controller;

import com.lite.common.dto.ResultResponse;
import com.lite.common.utils.ResultResponseUtils;
import com.lite.system.annotation.Permission;
import com.lite.system.annotation.RateLimit;
import com.lite.system.entity.PermissionId;
import net.dreamlu.mica.xss.core.XssCleanIgnore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class HelloWorldController {


    @RateLimit(limitTime = 60)
    @Permission(Min = PermissionId.DEFAULT)
    @RequestMapping(value = {"/hello","/helloWorld","/helloSpring"},method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
    public ResultResponse<String> HelloWorld(@NotBlank(message = "名称不能为空") @RequestParam String name) {
        return ResultResponseUtils.success("Hello,World! "+name);
    }

}
