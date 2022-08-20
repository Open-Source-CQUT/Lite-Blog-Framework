package com.liteweb.modules.auth.service;

import com.liteweb.modules.auth.dto.token.JwtTokenWrapper;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.auth.exception.UserNotFoundException;
import com.liteweb.modules.auth.vo.UserVo;
import com.liteweb.modules.common.dto.ResultResponse;

public interface AuthService {


    ResultResponse<JwtTokenWrapper> login(String mail, String password) throws AuthException;

    ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws AuthException;

    ResultResponse<JwtTokenWrapper> refreshToken() throws UserNotFoundException;

    ResultResponse<Boolean> logout();

    ResultResponse<Boolean> updateUserInfo(UserVo userVo) throws AuthException;

    ResultResponse<Boolean> changePassword(String mail, String oldPassword, String newPassword) throws AuthException;

}
