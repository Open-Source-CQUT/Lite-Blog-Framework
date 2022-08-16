package com.liteweb.service.auth.iml;

import com.alibaba.fastjson2.JSON;
import com.liteweb.convert.auth.UserConverter;
import com.liteweb.dao.auth.AuthMapper;
import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.dto.global.JwtToken;
import com.liteweb.dto.global.JwtTokenWrapper;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;
import com.liteweb.exception.auth.AuthException;
import com.liteweb.exception.auth.PasswordErrorException;
import com.liteweb.exception.auth.UserDuplicateException;
import com.liteweb.exception.auth.UserNotFoundException;
import com.liteweb.exception.lang.LiteBlogExceptionStatus;
import com.liteweb.service.auth.AuthService;
import com.liteweb.utils.auth.Authenticator;
import com.liteweb.utils.auth.JwtUtil;
import com.liteweb.utils.serializer.PasswordEncoder;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.ResultResponseUtils;
import com.liteweb.vo.Auth.UserTokenVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Service
public class AuthServiceIml implements AuthService {

    @Autowired
    UserConverter userConverter;

    @Autowired
    AuthMapper authMapper;

    @Autowired
    RedisCache redisCache;

    @Autowired
    Authenticator authenticator;

    @Override
    public ResultResponse<JwtTokenWrapper> login(String mail, String password) throws AuthException {

        User user = authMapper.getUser(mail).orElseGet(User::new);

        //用户不存在
        if (Objects.isNull(user.getMail()))
            throw new UserNotFoundException(LiteBlogExceptionStatus.USER_NOT_FOUND.value());

        //密码是否相同
        if (user.getPassword().equals(password))
            throw new PasswordErrorException(LiteBlogExceptionStatus.PASSWORD_ERROR.value());

        //将对象转换成dto
        UserTokenVo userVo = userConverter.entityToTokenVo(user);

        //生成accessToken
        JwtToken accessToken = authenticator.processAndGetAccessToken(userVo);

        //生成refresh-token
        JwtToken refreshToken = authenticator.processAndGetRefreshToken(userVo);

        return ResultResponseUtils.success(
                new JwtTokenWrapper(accessToken, refreshToken),
                LiteBlogExceptionStatus.LOGIN_OK.value());
    }

    @Override
    public ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws UserDuplicateException {

        //转换成实体类
        User newUser = userConverter.dtoToEntity(userNormalDto);


        if (!Objects.isNull(authMapper.getUser(newUser.getMail())
                .orElseGet(User::new)
                .getMail()))
            throw new UserDuplicateException(LiteBlogExceptionStatus.USER_ALREADY_EXIST.value());

        //sha1加密
        newUser.setPassword(PasswordEncoder.enCode(newUser.getPassword()));
        //默认设置为普通用户
        newUser.setRoleId(0);

        if (!authMapper.insertUser(newUser))
            return ResultResponseUtils.error(
                    LiteBlogExceptionStatus.REGISTER_OK.code(),
                    LiteBlogExceptionStatus.REGISTER_FAIL.value());

        return ResultResponseUtils.success(true, LiteBlogExceptionStatus.REGISTER_OK.value());
    }

    @Override
    public ResultResponse<JwtTokenWrapper> refreshToken(HttpServletRequest request) {

        UserTokenVo refreshPayload = JSON.parseObject(
                JwtUtil.parseRefreshJwt(
                        request.getHeader(
                                JwtUtil.JWT_REFRESH_KEY)).getSubject(), UserTokenVo.class);

        JwtToken accessToken = authenticator.processAndGetAccessToken(refreshPayload);

        return ResultResponseUtils.success(
                new JwtTokenWrapper(accessToken, null),
                LiteBlogExceptionStatus.ACCESS_REFRESH_OK.value()
        );
    }
}
