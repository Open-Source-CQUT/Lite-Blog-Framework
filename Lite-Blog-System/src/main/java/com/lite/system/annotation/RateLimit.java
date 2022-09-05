package com.lite.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Stranger
 * @version 1.0
 * @description: 访问限制注解
 * @date 2022/9/5 20:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    String value() default "";

    int limitTime() default 1;

    int maxCount() default 30;

}
