package com.liteweb.modules.auth.controller;

import com.liteweb.modules.auth.convert.UserConverter;
import com.liteweb.modules.auth.dto.token.JwtTokenWrapper;
import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.auth.exception.PasswordErrorException;
import com.liteweb.modules.auth.exception.UserDuplicateException;
import com.liteweb.modules.auth.exception.UserNotFoundException;
import com.liteweb.modules.auth.service.AuthService;
import com.liteweb.modules.auth.vo.user.UserVo;
import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.common.validation.groups.NormalGroups;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@Slf4j
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    AuthService service;

    @Autowired
    UserConverter userConverter;

    @Autowired
    HttpServletRequest httpRequest;

    @GetMapping("/login")
    public ResultResponse<JwtTokenWrapper> login(
            @RequestParam @Email @NotBlank String mail,
            @RequestParam @NotBlank String password)
            throws AuthException {

        return service.login(mail, password);
    }

    @PostMapping("/register")
    public ResultResponse<Boolean> register(
            @Validated(NormalGroups.Crud.Insert.class) @RequestBody UserVo userVo)
            throws UserDuplicateException {

        return service.register(userConverter.voToNormalDto(userVo));
    }

    @PostMapping("/changePassword")
    public ResultResponse<Boolean> changePassword(
            @RequestParam @Email @NotBlank String mail,
            @RequestParam @NotBlank String oldPassword,
            @RequestParam @NotBlank String newPassword)
            throws UserNotFoundException, PasswordErrorException {

        //TODO 修改密码
        return service.changePassword(mail, oldPassword, newPassword);
    }

    @PostMapping("/logout")
    public ResultResponse<Boolean> logout() {

        //TODO 注销登陆
        return service.logout(httpRequest);
    }

    @GetMapping("/refreshToken")
    public ResultResponse<JwtTokenWrapper> refreshToken()
            throws UserNotFoundException {
        return service.refreshToken(httpRequest);
    }

}
