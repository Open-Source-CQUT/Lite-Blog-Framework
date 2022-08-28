package com.lite.system.config;

import com.lite.system.entity.SystemApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "sys")
public class SystemConfig {

    private String redisMapKey = "SYS_API_INFO_MAP";

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value(("${server.port}"))
    private int port;

    private boolean logEnable;

    private Class<Map<String, SystemApi>> apiClas;

}
