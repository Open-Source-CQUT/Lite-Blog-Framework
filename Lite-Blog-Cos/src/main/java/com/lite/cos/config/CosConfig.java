package com.lite.cos.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties("cos")
@NoArgsConstructor
public class CosConfig {

    private String appId;

    private String secretId;

    private String secretKey;

    private String region;

    private String staticBucket;

    private String publicBucket;

    private String privateBucket;

    private String logBucket;

    private Integer connectionTimeout;

    private Integer socketTimeout;

    private Integer threadSizes;

    private Long multipartUploadThreshold;

    private Long minimumUploadPartSize;

    private String BaseUrl;

}
