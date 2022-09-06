package com.lite.auth.interceptor;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/6 15:18
 */
@Component
public class InterceptorManager {

    @Autowired
    WebApplicationContext webApplicationContext;

    public List<BaseInterceptor> getInterceptorList() {

        Map<String, BaseInterceptor> beanMap = webApplicationContext.getBeansOfType(BaseInterceptor.class);

        //将拦截器按照拦截顺序排序
        return beanMap.values().stream().sorted((beanA, beanB) -> {

            Order orderA = beanA.getClass().getAnnotation(Order.class);

            Order orderB = beanB.getClass().getAnnotation(Order.class);

            int orderFlagA = orderA == null ? Ordered.LOWEST_PRECEDENCE : orderA.value();

            int orderFlagB = orderB == null ? Ordered.LOWEST_PRECEDENCE : orderB.value();

            return orderFlagA - orderFlagB;
        }).collect(Collectors.toList());
    }
}
