package com.liteweb.modules.common.controller;

import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.common.utils.ResultResponseUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class HelloController {

    @GetMapping("/hello")
    public ResultResponse<String> Hello(@NotBlank(message = "名称不能为空") @RequestParam String name) {
        return ResultResponseUtils.success(name);
    }
}
