package com.lite.auth.interceptor.impl;

import com.lite.auth.config.CorsConfig;
import com.lite.auth.config.WebUrlConfig;
import com.lite.auth.interceptor.BaseInterceptor;
import com.lite.system.utils.SystemStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CORS拦截器，处理因复杂请求而产生的OPTION预请求
 * @author Stranger
 */
@Order(1)
@Component
public class CorsInterceptor extends BaseInterceptor {

    @Autowired
    CorsConfig corsConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //如果是Options请求,拦截后直接返回203
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {

            // * 这里因为拦截了OPTION请求，返回false的话是不会有响应头的，需要我们自己写返回头

            //设置允许的访问源
            response.setHeader("Access-Control-Allow-Origin", SystemStringUtils.concatWithComma(corsConfig.getAllowedOrigins()));
            //设置验证
            response.setHeader("Access-Control-Allow-Credentials", String.valueOf(corsConfig.getAllowedCredentials()));
            //设置允许的请求类型
            response.setHeader("Access-Control-Allow-Methods", SystemStringUtils.concatWithComma(corsConfig.getAllowedMethods()));
            //设置超时时间
            response.setHeader("Access-Control-Max-Age", String.valueOf(corsConfig.getMaxAge()));
            //设置允许的请求头参数
            response.setHeader("Access-Control-Allow-Headers", SystemStringUtils.concatWithComma(corsConfig.getAllowedHeaders()));

            //返回203
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());

            //拦截掉，不让继续往下走
            return false;
        }

        return true;
    }

    @Override
    public void loadUrlPath(WebUrlConfig webUrlConfig) {
        this.setExcludePath(webUrlConfig.getCorsExclude());
        this.setIncludePath(webUrlConfig.getCorsInclude());
    }
}
