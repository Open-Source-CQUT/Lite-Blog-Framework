package com.lite.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 16:20
 */
@Configuration
@EnableAsync
public class SystemAsyncConfig {


    @Bean("SysAsyncExecutor")
    public Executor asyncExecutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //设置初始线程池大小
        executor.setCorePoolSize(3);

        //设置线程池最大大小
        executor.setMaxPoolSize(10);

        //设置缓冲队列大小
        executor.setQueueCapacity(500);

        //设置线程最大空闲时间
        executor.setKeepAliveSeconds(30);

        //设置线程前缀
        executor.setThreadNamePrefix("SysAsync-");

        //初始化
        executor.initialize();

        return executor;
    }
}
