package com.liteweb.service.auth;

import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;
import com.liteweb.vo.Auth.UserVo;

public interface AuthService {


    ResultResponse<Boolean> login(String mail,String password);

    ResultResponse<Boolean> register(UserNormalDto userNormalDto);


}
