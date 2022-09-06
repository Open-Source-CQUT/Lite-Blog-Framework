package com.lite.auth.interceptor;

import com.lite.auth.config.WebUrlConfig;
import lombok.Data;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: 基础的拦截器，如果想要自定义拦截器必须继承BaseInterceptor,否则无法注册
 * @date 2022/9/6 15:16
 */
@Data
public abstract class BaseInterceptor implements HandlerInterceptor {

    /**
     * 拦截器拦截的路径
     */
    private List<String> includePath;

    /**
     * 拦截器放行的路径
     */
    private List<String> excludePath;

    /**
     * 此方法的作用在于加载自身的 includePath excludePath，子类必须重写
     * @param webUrlConfig 传入的URL配置类
     */
    public abstract void loadUrlPath(WebUrlConfig webUrlConfig);
}
