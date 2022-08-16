package com.liteweb.service.auth;

import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.dto.global.JwtTokenWrapper;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.exception.auth.AuthException;
import com.liteweb.exception.auth.PasswordErrorException;
import com.liteweb.exception.auth.UserDuplicateException;
import com.liteweb.exception.auth.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {


    ResultResponse<JwtTokenWrapper> login(String mail, String password) throws AuthException;

    ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws UserDuplicateException;

    ResultResponse<JwtTokenWrapper> refreshToken(HttpServletRequest request) throws UserNotFoundException;

    ResultResponse<Boolean> logout(HttpServletRequest request);

    ResultResponse<Boolean> changePassword(String mail, String oldPassword, String newPassword) throws UserNotFoundException, PasswordErrorException;

}
