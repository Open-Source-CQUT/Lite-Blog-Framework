package com.liteweb.service.auth;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;

public interface AuthService {


    ResultResponse<Boolean> login(String mail,String password);

    ResultResponse<Boolean> register(User user);



}
