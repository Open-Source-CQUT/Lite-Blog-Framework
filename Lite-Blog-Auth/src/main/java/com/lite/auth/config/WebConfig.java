package com.lite.auth.config;

import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.alibaba.fastjson2.support.spring.webservlet.view.FastJsonJsonView;
import com.lite.auth.interceptor.InterceptorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    WebUrlConfig webUrlConfig;

    @Autowired
    CorsConfig corsConfig;

    @Autowired
    InterceptorManager interceptorManager;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加自定义拦截器

        //设置多语言拦截配置
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        //注册拦截器
        interceptorManager.getInterceptorList().forEach(baseInterceptor -> {
            baseInterceptor.loadUrlPath(webUrlConfig);
            registry.addInterceptor(baseInterceptor)
                    .addPathPatterns(baseInterceptor.getIncludePath())
                    .excludePathPatterns(baseInterceptor.getExcludePath());
        });
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

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(new FastJsonJsonView());
    }

    /**
     * 替换掉springboot默认的jacksonHttpMessageConverter
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.add(0, converter);
    }
}
