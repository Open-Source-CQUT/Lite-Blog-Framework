package com.liteweb.controller;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.utils.tool.ResultResponseUtils;
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
