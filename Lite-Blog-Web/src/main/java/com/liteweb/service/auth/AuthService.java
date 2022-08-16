package com.liteweb.service.auth;

import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.dto.global.JwtTokenWrapper;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;
import com.liteweb.exception.auth.AuthException;
import com.liteweb.exception.auth.PasswordErrorException;
import com.liteweb.exception.auth.UserDuplicateException;
import com.liteweb.exception.auth.UserNotFoundException;
import com.liteweb.vo.Auth.UserVo;

public interface AuthService {


    ResultResponse<JwtTokenWrapper> login(String mail, String password) throws PasswordErrorException, AuthException;

    ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws UserDuplicateException;

    ResultResponse<JwtTokenWrapper> refreshToken(String mail) throws UserNotFoundException;

}
