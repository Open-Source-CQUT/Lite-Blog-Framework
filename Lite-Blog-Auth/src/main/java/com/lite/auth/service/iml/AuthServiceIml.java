package com.lite.auth.service.iml;

import com.alibaba.fastjson.JSON;
import com.lite.common.dto.ResultResponse;
import com.lite.common.dto.token.JwtToken;
import com.lite.common.dto.token.JwtTokenWrapper;
import com.lite.common.i18n.LocalMessages;
import com.lite.common.utils.JwtUtil;
import com.lite.auth.convert.UserConverter;
import com.lite.auth.dao.AuthMapper;
import com.lite.auth.dto.UserNormalDto;
import com.lite.auth.service.AuthService;
import com.lite.auth.utils.Authenticator;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.auth.vo.UserTokenVo;
import com.lite.auth.vo.UserVo;
import com.lite.common.utils.ResultResponseUtils;
import com.lite.auth.entity.User;
import com.lite.auth.exception.AuthException;
import com.lite.auth.exception.PasswordErrorException;
import com.lite.auth.exception.UserDuplicateException;
import com.lite.auth.exception.UserNotFoundException;
import com.lite.common.serializer.PasswordEncoder;
import com.lite.common.serializer.RedisCache;
import com.lite.mail.Vo.AuthMailVo;
import com.lite.mail.utils.MailUtils;
import com.lite.system.entity.PermissionId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    @Autowired
    LiteBlogContextUtils contextUtils;

    @Override
    public ResultResponse<JwtTokenWrapper> login(String mail, String password) throws AuthException {

        User user = authMapper.getUser(mail).orElseGet(User::new);

        //用户不存在
        if (Objects.isNull(user.getMail()))
            throw new UserNotFoundException(LocalMessages.get("error.user.auth.userNotFound"));

        //密码是否相同
        if (!user.getPassword().equals(PasswordEncoder.enCode(password)))
            throw new PasswordErrorException(LocalMessages.get("error.user.auth.password"));

        //将对象转换成dto
        UserTokenVo userVo = userConverter.entityToTokenVo(user);

        //生成accessToken
        JwtToken accessToken = authenticator.processAndGetAccessToken(userVo);

        //生成refresh-token
        JwtToken refreshToken = authenticator.processAndGetRefreshToken(userVo);

        return ResultResponseUtils.success(
                new JwtTokenWrapper(accessToken, refreshToken),
                LocalMessages.get("success.user.auth.login"));
    }

    @Override
    public ResultResponse<Boolean> register(UserNormalDto userNormalDto) throws AuthException {

        String key = MailUtils.getMailRedisKey(userNormalDto.getMail());
        //进行验证码比对
        AuthMailVo authMailVo = JSON.toJavaObject(redisCache.getCacheObject(key), AuthMailVo.class);

        //如果redis中不存在 或者 验证不匹配
        if (Objects.isNull(authMailVo) || !authMailVo.getAuthCode().equals(userNormalDto.getAuthCode()))
            throw new AuthException(HttpStatus.BAD_REQUEST.value(), LocalMessages.get("error.user.auth.authCodeFail"));

        //转换成实体类
        User newUser = userConverter.dtoToEntity(userNormalDto);

        if (!Objects.isNull(authMapper.getUser(newUser.getMail())
                .orElseGet(User::new)
                .getMail()))
            throw new UserDuplicateException(LocalMessages.get("error.user.auth.userExisted"));

        //sha1加密
        newUser.setPassword(PasswordEncoder.enCode(newUser.getPassword()));

        //默认设置为普通用户
        newUser.setPermissionId(PermissionId.DEFAULT.val());

        if (!authMapper.insertUser(newUser))
            throw new AuthException(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalMessages.get("error.user.auth.register"));

        //操作成功后删除redis中的缓存
        redisCache.deleteObject(key);

        return ResultResponseUtils.success(true, LocalMessages.get("success.user.auth.register"));
    }

    @Override
    public ResultResponse<JwtTokenWrapper> refreshToken() {

        UserTokenVo refreshPayload = contextUtils.getUserContextInfo();

        JwtToken accessToken = authenticator.processAndGetAccessToken(refreshPayload);

        return ResultResponseUtils.success(
                new JwtTokenWrapper(accessToken, null),
                LocalMessages.get("success.jwt.access.refresh")
        );
    }

    @Override
    public ResultResponse<Boolean> logout() {

        //能走到这里说明已经通过了拦截器的校验，所以这里的代码不用做安全性检查，如果有发生异常直接抛出交给全局异常处理即可
        //从请求头中读取数据
        UserTokenVo userTokenVo = contextUtils.getUserContextInfo();

        //获取key
        String accessKey = JwtUtil.getRedisAccessKey(userTokenVo.getMail(), userTokenVo.getUuid());

        String refreshKey = JwtUtil.getRedisRefreshKey(userTokenVo.getMail(), userTokenVo.getUuid());

        //是否注销成功
        if (!redisCache.deleteObject(accessKey) || !redisCache.deleteObject(refreshKey))
            return ResultResponseUtils.error(false, LocalMessages.get("error.user.auth.logout"));

        return ResultResponseUtils.success(true, LocalMessages.get("success.user.auth.logout"));
    }

    @Override
    public ResultResponse<Boolean> updateUserInfo(UserVo userVo) throws AuthException {

        User user = userConverter.voToEntity(userVo);

        if (Objects.isNull(authMapper.getUser(user.getMail()).orElseGet(User::new).getMail()))
            throw new UserNotFoundException(LocalMessages.get("error.user.auth.userNotFound"));

        if (!authMapper.updateUserInfo(user))
            throw new AuthException(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalMessages.get("error.user.auth.update"));

        return ResultResponseUtils.success(true, LocalMessages.get("success.user.auth.update"));
    }

    @Override
    public ResultResponse<Boolean> changePassword(String mail, String oldPassword, String newPassword)
            throws AuthException {

        User user = authMapper.getUser(mail).orElseGet(User::new);

        //验证用户是否存在
        if (Objects.isNull(user.getMail()))
            throw new UserNotFoundException(LocalMessages.get("error.user.auth.userNotFound"));

        //验证密码是否正确
        if (!PasswordEncoder.enCode(oldPassword).equals(user.getPassword()))
            throw new PasswordErrorException(LocalMessages.get("error.user.auth.password"));

        //是否成功修改密码
        if (!authMapper.updateUserPassword(mail, PasswordEncoder.enCode(newPassword)))
            throw new AuthException(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalMessages.get("error.user.auth.passwordChange"));

        return ResultResponseUtils.success(true, LocalMessages.get("success.user.auth.passwordChange"));
    }
}
