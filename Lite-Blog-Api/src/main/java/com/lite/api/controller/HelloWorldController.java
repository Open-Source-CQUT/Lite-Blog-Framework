package com.lite.api.controller;

import com.lite.common.dto.ResultResponse;
import com.lite.common.utils.ResultResponseUtils;
import com.lite.system.annotation.Permission;
import com.lite.system.entity.PermissionId;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@Permission(Min = PermissionId.ADMIN)
public class HelloWorldController {


    //ADMIN代表只有管理员有权限访问此接口
    @Permission(Min = PermissionId.ADMIN)
    @RequestMapping(value = {"/hello","/helloWorld","/helloSpring"},method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
    public ResultResponse<String> Hello(@NotBlank(message = "名称不能为空") @RequestParam String name) {
        return ResultResponseUtils.success("Hello,World! "+name);
    }
}
