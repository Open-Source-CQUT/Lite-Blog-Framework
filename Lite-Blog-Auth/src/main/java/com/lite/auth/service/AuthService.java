package com.lite.auth.service;


import com.lite.auth.dto.UserNormalDto;
import com.lite.auth.exception.AuthException;
import com.lite.auth.exception.UserNotFoundException;
import com.lite.auth.vo.UserVo;
import com.lite.common.dto.token.JwtTokenWrapper;

public interface AuthService {


    JwtTokenWrapper login(String mail, String password) throws AuthException;

    Boolean register(UserNormalDto userNormalDto) throws AuthException;

    JwtTokenWrapper refreshToken() throws UserNotFoundException;

    Boolean logout();

    Boolean updateUserInfo(UserVo userVo) throws AuthException;

    Boolean changePassword(String oldPassword, String newPassword) throws AuthException;

    Boolean forgetPassword(String mail, String newPassword, String code) throws AuthException;

}
