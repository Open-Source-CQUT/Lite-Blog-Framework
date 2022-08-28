package com.lite.auth.interceptor;

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
 */
@Order(1)
@Component
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //如果是Options请求,拦截后直接返回200
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())){
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return true;
    }
}
