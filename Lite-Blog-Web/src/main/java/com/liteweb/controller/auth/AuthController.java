package com.liteweb.controller.auth;

import com.liteweb.convert.auth.UserConverter;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.service.auth.AuthService;
import com.liteweb.validation.Groups.NormalGroups;
import com.liteweb.vo.Auth.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    AuthService service;

    @Autowired
    UserConverter userConverter;

    @PostMapping("/register")
    public ResultResponse<Boolean> register(@Validated(NormalGroups.Crud.Insert.class) UserVo userVo) {

        //TODO 注册接口
        return service.register(userConverter.voToNormalDto(userVo));
    }

    @GetMapping("/login")
    public ResultResponse<Boolean> login(@RequestParam @Email @NotBlank String mail, @RequestParam @NotBlank String password) {

        //TODO 登陆接口
        return service.login(mail,password);
    }
}
