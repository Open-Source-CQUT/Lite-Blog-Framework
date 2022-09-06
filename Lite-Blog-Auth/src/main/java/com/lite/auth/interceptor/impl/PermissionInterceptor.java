package com.lite.auth.interceptor.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.lite.auth.config.WebUrlConfig;
import com.lite.auth.exception.AuthException;
import com.lite.auth.interceptor.BaseInterceptor;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.auth.vo.UserTokenVo;
import com.lite.common.i18n.SystemMessages;
import com.lite.common.serializer.RedisCache;
import com.lite.system.config.SystemConfig;
import com.lite.system.entity.SystemApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author Stranger
 * @version 1.0
 * @description: 接口访问权限拦截器，用户处理用户访问权限，最后一个执行的拦截器
 * @date 2022/8/27 19:53
 */
@Slf4j
@Order(4)
@Component
public class PermissionInterceptor extends BaseInterceptor {

    @Autowired
    SystemConfig systemConfig;

    @Autowired
    RedisCache redisCache;
    @Autowired
    LiteBlogContextUtils contextUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //取出用户访问的路径
        String requestUrl = request.getRequestURI();

        //取出访问用户的信息，能够走到这里必定是通过了AuthInterceptor的校验
        UserTokenVo userContextInfo = contextUtils.getLocalUserInfo();

        //读取缓存中的API信息进行比对
        Map<String, JSONObject> cacheMap = contextUtils.getRedisCache().getCacheMap(systemConfig.getRedisMapKey());

        //取得API信息
        SystemApi systemApi = JSON.toJavaObject(cacheMap.get(requestUrl), SystemApi.class);

        if (Objects.isNull(systemApi)) {
            throw new AuthException(HttpStatus.NOT_FOUND.value(), SystemMessages.get("http.code.404"));
        }

        if (systemApi.getEnable() && systemApi.getPermissionId() > userContextInfo.getPermissionId()) {
            throw new AuthException(HttpStatus.FORBIDDEN.value(), SystemMessages.get("error.user.auth.notPermit"));
        }

        return true;
    }

    @Override
    public void loadUrlPath(WebUrlConfig webUrlConfig) {
        this.setIncludePath(webUrlConfig.getAccessInclude());
        this.setExcludePath(webUrlConfig.getAccessExclude());
    }
}
