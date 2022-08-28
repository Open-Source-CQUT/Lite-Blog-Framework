package com.lite.common.config.mp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Data
@Configuration
@ConfigurationProperties("auto-inject")
public class AutoInjectConfig {

    private List<String> timeInsert;

    private List<String> timeUpdate;

}
