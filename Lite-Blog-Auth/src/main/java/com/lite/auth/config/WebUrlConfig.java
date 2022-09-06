package com.lite.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mvc.interceptor")
public class WebUrlConfig {

    private Boolean enable;

    /**
     * 访问放行url
     */
    private List<String> accessExclude;

    /**
     * 访问拦截url
     */
    private List<String> accessInclude;

    /**
     * 刷新token放行url
     */
    private List<String> refreshExclude;

    /**
     * 刷新token拦截url
     */
    private List<String> refreshInclude;

    /**
     * 跨域拦截url
     */
    private List<String> corsInclude;
    /**
     * 跨域放行url
     */
    private List<String> corsExclude;

    /**
     * 统计拦截url
     */
    private List<String> statisticInclude;

    /**
     * 统计放行url
     */
    private List<String> statisticExclude;
}
