package com.liteweb.config;

import com.liteweb.modules.auth.interceptor.AuthInterceptor;
import com.liteweb.modules.auth.interceptor.RefreshInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    RefreshInterceptor refreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加自定义拦截器

        //access-token 拦截器
        registry.addInterceptor(authInterceptor)
                //放行url
                .excludePathPatterns(Arrays.asList(
                        "/auth/login",
                        "/auth/register",
                        "/auth/refreshToken",
                        "/error"
                ))
                //拦截url
                .addPathPatterns("/**");

        //refresh-token 拦截器
        registry.addInterceptor(refreshInterceptor)
                //拦截url
                .addPathPatterns("/auth/refreshToken");

    }
}
