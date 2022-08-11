package com.liteweb.service.auth.iml;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;
import com.liteweb.service.auth.AuthService;

public class AuthServiceIml implements AuthService {

    @Override
    public ResultResponse<Boolean> login(String mail, String password) {
        return null;
    }

    @Override
    public ResultResponse<Boolean> register(User user) {
        return null;
    }
}
