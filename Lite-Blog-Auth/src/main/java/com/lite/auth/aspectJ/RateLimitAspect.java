package com.lite.auth.aspectJ;

import com.lite.auth.exception.SystemBusyException;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.common.entity.RedisEvalRes;
import com.lite.common.i18n.SystemMessages;
import com.lite.common.serializer.RedisCache;
import com.lite.common.serializer.RedisJsonScript;
import com.lite.system.annotation.RateLimit;
import com.lite.system.entity.LimitType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @author Stranger
 * @version 1.0
 * @description: 限流切面类, 对于加了指定注解的接口进行限流
 * @date 2022/9/5 20:25
 */
@Aspect
@Component
public class RateLimitAspect {


    @Autowired
    RedisCache redisCache;
    @Autowired
    LiteBlogContextUtils contextUtils;


    @Before(value = "@annotation(com.lite.system.annotation.RateLimit)")
    public void rateLimitInterceptor(JoinPoint joinpoint) throws SystemBusyException {

        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();

        Method method = methodSignature.getMethod();

        //获取限流注解
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        if (Objects.isNull(rateLimit)) return;

        //获取限流类型
        LimitType type = rateLimit.type();

        StringBuilder keuBuilder = null;

        switch (type) {
            //Token类型
            case Token: {
                keuBuilder = new StringBuilder(contextUtils.getLocalUserInfo().getUuid());
            }
            break;
            //Ip类型
            case IpAddress: {
                keuBuilder = new StringBuilder(contextUtils.getClientIpAddress());
            }
            break;
        }

        keuBuilder
                .append(".")
                .append(method.getDeclaringClass())
                .append(".")
                .append(method.getName())
                .append(".")
                .append(Arrays.toString(method.getParameters()))
                .append(".")
                .append(method.getReturnType());

        RedisEvalRes evalRes = redisCache.execute(
                RedisJsonScript.of(new ClassPathResource("lua/ipLimit.lua")),
                Collections.singletonList(keuBuilder.toString()),
                rateLimit.limitTime(), rateLimit.maxCount());

        Long resultCode = evalRes.getResult(Long.class);

        if (resultCode == -1) {
            throw new SystemBusyException(SystemMessages.get("limit.outCount"));
        }
    }
}
