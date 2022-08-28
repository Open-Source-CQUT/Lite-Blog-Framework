package com.lite.auth.utils;

import com.lite.common.serializer.RedisCache;
import com.lite.common.utils.JwtUtil;
import com.lite.auth.vo.UserTokenVo;
import com.lite.system.config.SystemConfig;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Component
public class LiteBlogContextUtils {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    RedisCache redisCache;

    @Autowired
    WebApplicationContext webApplicationContext;

    //从header中获取payload
    public UserTokenVo getUserContextInfo() {
        return JwtUtil.parseObject(request.getHeader(JwtUtil.JWT_ACCESS_KEY), UserTokenVo.class, JwtUtil.JWT_ACCESS_KEY);
    }

    public void setResponseStatus(Integer status) {
        response.setStatus(status);
    }
}
