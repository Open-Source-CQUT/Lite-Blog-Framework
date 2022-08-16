package com.liteweb.controller.auth;

import com.liteweb.convert.auth.UserConverter;
import com.liteweb.dto.global.JwtTokenWrapper;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.exception.auth.AuthException;
import com.liteweb.exception.auth.UserDuplicateException;
import com.liteweb.exception.auth.UserNotFoundException;
import com.liteweb.service.auth.AuthService;
import com.liteweb.validation.Groups.NormalGroups;
import com.liteweb.vo.Auth.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResultResponse<Boolean> register(
            @Validated(NormalGroups.Crud.Insert.class) @RequestBody UserVo userVo)
            throws UserDuplicateException {

        return service.register(userConverter.voToNormalDto(userVo));
    }

    @GetMapping("/login")
    public ResultResponse<JwtTokenWrapper> login(
            @RequestParam @Email @NotBlank String mail,
            @RequestParam @NotBlank String password)
            throws AuthException{

        return service.login(mail, password);
    }

    @GetMapping("/refreshToken")
    public ResultResponse<JwtTokenWrapper> refreshToken(@RequestParam @NotBlank String mail)
            throws UserNotFoundException {
        return service.refreshToken(mail);
    }

}
