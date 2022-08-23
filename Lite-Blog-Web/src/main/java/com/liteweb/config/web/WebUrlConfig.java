package com.liteweb.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mvc.interceptor")
public class WebUrlConfig {

    private Boolean enable;


    private List<String> accessExclude;

    private List<String> accessInclude;

    private List<String> refreshExclude;

    private List<String> refreshInclude;

    private List<String> corsInclude;
}
