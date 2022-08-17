package com.liteweb.modules.auth.service;

import com.liteweb.modules.auth.dto.token.JwtTokenWrapper;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.auth.exception.PasswordErrorException;
import com.liteweb.modules.auth.exception.UserDuplicateException;
import com.liteweb.modules.auth.exception.UserNotFoundException;
import com.liteweb.modules.common.dto.ResultResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {


    ResultResponse<JwtTokenWrapper> login(String mail, String password) throws AuthException;

    ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws UserDuplicateException;

    ResultResponse<JwtTokenWrapper> refreshToken(HttpServletRequest request) throws UserNotFoundException;

    ResultResponse<Boolean> logout(HttpServletRequest request);

    ResultResponse<Boolean> changePassword(String mail, String oldPassword, String newPassword) throws UserNotFoundException, PasswordErrorException;

}
