package com.liteweb.modules.auth.interceptor;

import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.common.exception.lang.LiteBlogExceptionStatus;
import com.liteweb.utils.auth.Authenticator;
import com.liteweb.utils.auth.JwtUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RefreshInterceptor implements HandlerInterceptor {

    @Autowired
    Authenticator authenticator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            //获取refresh token
            String refreshToken = request.getHeader(JwtUtil.JWT_REFRESH_KEY);

            //获取accessToken
            String accessToken = request.getHeader(JwtUtil.JWT_ACCESS_KEY);

            //断言
            Assert.notNull(refreshToken, LiteBlogExceptionStatus.REFRESH_NULL.value());
            Assert.notNull(accessToken, LiteBlogExceptionStatus.ACCESS_EXPIRED.value());

            //校验
            if (!authenticator.authenticateRefreshToken(refreshToken, accessToken))
                throw new AuthException();

            //因为获取的是refresh-token，此时再做时效判断没有意义，解析失败一律403，需要重新登陆获取refresh-token
        } catch (Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.toString());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
