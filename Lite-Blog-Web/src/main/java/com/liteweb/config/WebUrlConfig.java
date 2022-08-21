package com.liteweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "interceptor")
public class WebUrlConfig {

    List<String> accessExclude;

    List<String> accessInclude;

    List<String> refreshExclude;

    List<String> refreshInclude;
}
