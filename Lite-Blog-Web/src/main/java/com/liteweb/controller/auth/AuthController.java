package com.liteweb.controller.auth;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResultResponse<Boolean> register(@RequestBody User user){

        //TODO 注册接口
        return null;
    }

    @GetMapping("/login")
    public ResultResponse<Boolean> login(@RequestParam String mail,@RequestParam String password){

        //TODO 登陆接口
        return null;
    }
}
