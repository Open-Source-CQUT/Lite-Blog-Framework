package com.lite.auth.interceptor.impl;

import com.lite.auth.config.WebUrlConfig;
import com.lite.auth.interceptor.BaseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Stranger
 * @version 1.0
 * @description: 接口请求计时拦截器
 * @date 2022/9/6 15:08
 */
@Slf4j
@Order(0)
@Component
public class TimeStatisticInterceptor extends BaseInterceptor {

    @Autowired
    WebUrlConfig webUrlConfig;

    private final ThreadLocal<StopWatch> apiStopWatch = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //开始计时任务
        apiStopWatch.set(new StopWatch());
        apiStopWatch.get().start();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //停止计时任务
        apiStopWatch.get().stop();
        //输出日志
        log.info("本次请求总共耗时: {}秒",apiStopWatch.get().getTotalTimeSeconds());
        //移除threadlocal
        apiStopWatch.remove();
    }

    @Override
    public void loadUrlPath(WebUrlConfig webUrlConfig) {
        this.setIncludePath(webUrlConfig.getStatisticInclude());
        this.setExcludePath(webUrlConfig.getStatisticExclude());
    }
}
