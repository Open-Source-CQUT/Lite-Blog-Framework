package com.liteweb.config;

import com.liteweb.interceptor.auth.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加自定义拦截器

        registry.addInterceptor(authInterceptor)
                .excludePathPatterns(Arrays.asList(
                        "/auth/login",
                        "/auth/register",
                        "/hello"
                ))
                .addPathPatterns("/**");

    }
}
