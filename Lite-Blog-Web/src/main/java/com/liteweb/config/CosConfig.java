package com.liteweb.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(ignoreResourceNotFound = true, encoding = "UTF-8", value = "classpath:cos.yml")
@ConfigurationProperties("cos")
@NoArgsConstructor
public class CosConfig {

    @Value("appId")
    private String appId;

    @Value("secretId")
    private String secretId;

    @Value("secretKey")
    private String secretKey;

    @Value("region")
    private String region;

    @Value("mainBucket")
    private String mainBucket;

    @Value("logBucket")
    private String logBucket;

}
