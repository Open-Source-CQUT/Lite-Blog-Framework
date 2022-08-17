package com.liteweb.modules.auth.service.iml;

import com.alibaba.fastjson2.JSON;
import com.liteweb.modules.auth.convert.UserConverter;
import com.liteweb.modules.auth.dao.AuthMapper;
import com.liteweb.modules.auth.dto.token.JwtToken;
import com.liteweb.modules.auth.dto.token.JwtTokenWrapper;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.entity.User;
import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.auth.exception.PasswordErrorException;
import com.liteweb.modules.auth.exception.UserDuplicateException;
import com.liteweb.modules.auth.exception.UserNotFoundException;
import com.liteweb.modules.auth.service.AuthService;
import com.liteweb.modules.auth.vo.user.UserTokenVo;
import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.common.exception.lang.LiteBlogExceptionStatus;
import com.liteweb.utils.auth.Authenticator;
import com.liteweb.utils.auth.JwtUtil;
import com.liteweb.utils.serializer.PasswordEncoder;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.ResultResponseUtils;
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
        if (!user.getPassword().equals(PasswordEncoder.enCode(password)))
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

    @Override
    public ResultResponse<Boolean> logout(HttpServletRequest request) {

        //能走到这里说明已经通过了拦截器的校验，所以这里的代码不用做安全性检查，如果有发生异常直接抛出交给全局异常处理即可
        //从请求头中读取数据
        UserTokenVo userTokenVo = JSON.parseObject(
                JwtUtil.parseAccessJwt(
                        request.getHeader(JwtUtil.JWT_ACCESS_KEY)).getSubject(), UserTokenVo.class);

        //获取key
        String accessKey = JwtUtil.getRedisAccessKey(userTokenVo.getMail(), userTokenVo.getUuid());

        String refreshKey = JwtUtil.getRedisRefreshKey(userTokenVo.getMail(), userTokenVo.getUuid());

        //是否注销成功
        if (!redisCache.deleteObject(accessKey) || !redisCache.deleteObject(refreshKey))
            return ResultResponseUtils.error(false, LiteBlogExceptionStatus.LOGOUT_FAIL.value());

        return ResultResponseUtils.success(true, LiteBlogExceptionStatus.LOGOUT_OK.value());
    }

    @Override
    public ResultResponse<Boolean> changePassword(String mail, String oldPassword, String newPassword)
            throws UserNotFoundException, PasswordErrorException {

        User user = authMapper.getUser(mail).orElseGet(User::new);

        //验证用户是否存在
        if (Objects.isNull(user.getMail()))
            throw new UserNotFoundException(LiteBlogExceptionStatus.USER_NOT_FOUND.value());

        //验证密码是否正确
        if (!PasswordEncoder.enCode(oldPassword).equals(user.getPassword()))
            throw new PasswordErrorException(LiteBlogExceptionStatus.PASSWORD_ERROR.value());

        //是否成功修改密码
        if (!authMapper.updateUserPassword(mail, PasswordEncoder.enCode(newPassword)))
            return ResultResponseUtils.error(false, LiteBlogExceptionStatus.PASSWORD_CHANGE_FAIL.value());

        return ResultResponseUtils.success(true, LiteBlogExceptionStatus.PASSWORD_CHANGE_OK.value());
    }
}
