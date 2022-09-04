package com.lite.auth.config;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.alibaba.fastjson2.support.spring.webservlet.view.FastJsonJsonView;
import com.lite.auth.interceptor.AuthInterceptor;
import com.lite.auth.interceptor.CorsInterceptor;
import com.lite.auth.interceptor.PermissionInterceptor;
import com.lite.auth.interceptor.RefreshInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    CorsInterceptor corsInterceptor;
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    RefreshInterceptor refreshInterceptor;

    @Autowired
    PermissionInterceptor permissionInterceptor;

    @Autowired
    WebUrlConfig webUrlConfig;

    @Autowired
    CorsConfig corsConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加自定义拦截器

        //设置多语言拦截配置
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        //配置中是否开启拦截器
        if (!webUrlConfig.getEnable())
            return;

        //Cors拦截器
        registry.addInterceptor(corsInterceptor)
                //拦截所有url
                .addPathPatterns(webUrlConfig.getCorsInclude());

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

        //Permission拦截器
        registry.addInterceptor(permissionInterceptor)
                .excludePathPatterns(webUrlConfig.getAccessExclude())
                .addPathPatterns(webUrlConfig.getAccessInclude());

    }

    //cors配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(corsConfig.getPathMapping())
                .allowedOrigins(corsConfig.getAllowedOrigins())
                .allowedMethods(corsConfig.getAllowedMethods())
                .allowedHeaders(corsConfig.getAllowedHeaders())
                .maxAge(corsConfig.getMaxAge())
                .allowCredentials(false);
    }

    //自定义解析本地语言解析器
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(new FastJsonJsonView());
    }

    /**
     * 替换掉springboot默认的jacksonHttpMessageConverter
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.add(0, converter);
    }
}
