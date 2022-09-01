package com.lite.auth.service;


import com.lite.common.dto.ResultResponse;
import com.lite.common.dto.token.JwtTokenWrapper;
import com.lite.auth.dto.UserNormalDto;
import com.lite.auth.exception.AuthException;
import com.lite.auth.exception.UserNotFoundException;
import com.lite.auth.vo.UserVo;

public interface AuthService {


    JwtTokenWrapper login(String mail, String password) throws AuthException;

    Boolean register(UserNormalDto userNormalDto) throws AuthException;

    JwtTokenWrapper refreshToken() throws UserNotFoundException;

    Boolean logout();

    Boolean updateUserInfo(UserVo userVo) throws AuthException;

    Boolean changePassword(String mail, String oldPassword, String newPassword) throws AuthException;

}
