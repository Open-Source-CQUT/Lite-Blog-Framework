package com.lite.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("spring.mvc.cors")
public class CorsConfig {

    private String pathMapping;

    private List<String> allowedOrigins;

    private List<String> allowedHeaders;

    private List<String> allowedMethods;

    private Boolean allowedCredentials;

    private Long maxAge;

    public String[] getAllowedMethods() {
        return toArray(allowedMethods);
    }

    public String getPathMapping() {
        return pathMapping;
    }

    public String[] getAllowedOrigins() {
        return toArray(allowedOrigins);
    }

    public String[] getAllowedHeaders() {
        return toArray(allowedHeaders);
    }


    private String[] toArray(List<String> list){
        return list.toArray(new String[0]);
    }
}
