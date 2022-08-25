package com.lite.mail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    private String host;

    private String username;

    private String password;

    private String defaultEncoding;
}
