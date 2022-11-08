package com.lite.auth.interceptor.impl;

import com.lite.auth.config.WebConfig;
import com.lite.auth.config.WebUrlConfig;
import com.lite.auth.interceptor.BaseInterceptor;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.common.i18n.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Stranger
 * @version 1.0
 * @description: Ip地址拦截器
 * @date 2022/11/8 16:22
 */
@Slf4j
@Component
public class IpAddressInterceptor extends BaseInterceptor {


    @Autowired
    WebConfig webConfig;

    @Autowired
    LiteBlogContextUtils contextUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(SystemMessages.get("ip.query", contextUtils.getClientIpAddress()));
        return true;
    }

    @Override
    public void loadUrlPath(WebUrlConfig webUrlConfig) {
        this.setIncludePath(webUrlConfig.getStatisticInclude());
        this.setExcludePath(webUrlConfig.getStatisticExclude());
    }
}
