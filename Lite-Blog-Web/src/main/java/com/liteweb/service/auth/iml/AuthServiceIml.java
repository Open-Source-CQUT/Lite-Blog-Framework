package com.liteweb.service.auth.iml;

import com.liteweb.convert.auth.UserConverter;
import com.liteweb.dao.auth.AuthMapper;
import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.dto.global.JwtTokenWrapper;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.dto.global.JwtToken;
import com.liteweb.entity.auth.User;
import com.liteweb.exception.auth.AuthException;
import com.liteweb.exception.auth.PasswordErrorException;
import com.liteweb.exception.auth.UserDuplicateException;
import com.liteweb.exception.auth.UserNotFoundException;
import com.liteweb.service.auth.AuthService;
import com.liteweb.utils.auth.Authenticator;
import com.liteweb.utils.serializer.PasswordEncoder;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.ResultResponseUtils;
import com.liteweb.vo.Auth.UserTokenVo;
import com.liteweb.vo.Auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
            throw new UserNotFoundException("用户不存在");

        //密码是否相同
        if (user.getPassword().equals(password))
            throw new PasswordErrorException("密码错误");

        //将对象转换成dto
        UserTokenVo userVo = userConverter.entityToTokenVo(user);

        //生成accessToken
        JwtToken accessToken =  authenticator.processAndGetAccessToken(userVo);

        //生成refresh-token
        JwtToken refreshToken = authenticator.processAndGetRefreshToken(userVo);

        return ResultResponseUtils.success(
                new JwtTokenWrapper(accessToken, refreshToken),
                "登陆成功");
    }

    @Override
    public ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws UserDuplicateException {

        //转换成实体类
        User newUser = userConverter.dtoToEntity(userNormalDto);


        if (!Objects.isNull(authMapper.getUser(newUser.getMail())
                .orElseGet(User::new)
                .getMail()))
            throw new UserDuplicateException("用户已存在");

        //sha1加密
        newUser.setPassword(PasswordEncoder.enCode(newUser.getPassword()));
        //默认设置为普通用户
        newUser.setRoleId(0);

        if (!authMapper.insertUser(newUser))
            return ResultResponseUtils.success(false, "注册失败");

        return ResultResponseUtils.success(true, "注册成功");
    }

    @Override
    public ResultResponse<JwtTokenWrapper> refreshToken(String mail) throws UserNotFoundException {

        User user = authMapper.getUser(mail).orElseGet(User::new);

        if (Objects.isNull(user.getMail()))
            throw new UserNotFoundException("用户不存在");

        UserTokenVo userVo = userConverter.entityToTokenVo(user);

        JwtToken accessToken = authenticator.processAndGetAccessToken(userVo);

        return ResultResponseUtils.success(
                new JwtTokenWrapper(accessToken, null),
                "access-token刷新成功"
        );
    }
}
