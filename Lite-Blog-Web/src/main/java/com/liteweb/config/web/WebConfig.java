package com.liteweb.config.web;

import com.liteweb.modules.auth.interceptor.AuthInterceptor;
import com.liteweb.modules.auth.interceptor.RefreshInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    RefreshInterceptor refreshInterceptor;

    @Autowired
    WebUrlConfig webUrlConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加自定义拦截器

        //设置多语言拦截配置
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        //配置中是否开启拦截器
        if (!webUrlConfig.getEnable())
            return;

        //access-token 拦截器
        registry.addInterceptor(authInterceptor)
                //放行url
                .excludePathPatterns(webUrlConfig.getAccessExclude())
                //拦截url
                .addPathPatterns(webUrlConfig.getAccessInclude());

        //refresh-token 拦截器
        registry.addInterceptor(refreshInterceptor)
                //拦截url
                .addPathPatterns(webUrlConfig.getRefreshInclude());


    }

    //自定义解析本地语言解析器
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }
}