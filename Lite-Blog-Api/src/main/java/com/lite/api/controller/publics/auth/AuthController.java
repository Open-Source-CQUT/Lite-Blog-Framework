package com.lite.api.controller.publics.auth;

import com.lite.common.dto.ResultResponse;
import com.lite.common.dto.token.JwtTokenWrapper;
import com.lite.common.groups.NormalGroups;
import com.lite.auth.convert.UserConverter;
import com.lite.auth.exception.AuthException;
import com.lite.auth.exception.UserNotFoundException;
import com.lite.auth.service.AuthService;
import com.lite.auth.vo.UserVo;
import com.lite.common.i18n.SystemMessages;
import com.lite.common.utils.ResultResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public ResultResponse<JwtTokenWrapper> login(
            @RequestParam @Email() @NotBlank String mail,
            @RequestParam @NotBlank String password)
            throws AuthException {

        return ResultResponseUtils.success(
                service.login(mail, password),
                SystemMessages.get("success.user.auth.login"));
    }

    @PostMapping("/register")
    public ResultResponse<Boolean> register(
            @Validated(NormalGroups.Crud.Insert.class) @RequestBody UserVo userVo)
            throws AuthException {


        return service.register(userConverter.voToNormalDto(userVo)) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.user.auth.register")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.user.auth.register"));
    }

    @PostMapping("/updateInfo")
    public ResultResponse<Boolean> updateInfo(@RequestBody UserVo userVo) throws AuthException {
        return service.updateUserInfo(userVo) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.user.auth.update")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.user.auth.update"));
    }

    @PostMapping("/changePassword")
    public ResultResponse<Boolean> changePassword(
            @RequestParam @Email @NotBlank String mail,
            @RequestParam @NotBlank String oldPassword,
            @RequestParam @NotBlank String newPassword)
            throws AuthException {

        //TODO 修改密码
        return service.changePassword(mail, oldPassword, newPassword) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.user.auth.passwordChange")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.user.auth.passwordChange"));
    }

    @PostMapping("/logout")
    public ResultResponse<Boolean> logout() {

        //TODO 注销登陆
        return service.logout() ?
                ResultResponseUtils.success(true, SystemMessages.get("success.user.auth.logout")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.user.auth.logout"));
    }

    @GetMapping("/refreshToken")
    public ResultResponse<JwtTokenWrapper> refreshToken()
            throws UserNotFoundException {

        return ResultResponseUtils.success(
                service.refreshToken(),
                SystemMessages.get("success.jwt.access.refresh")
        );
    }

}
