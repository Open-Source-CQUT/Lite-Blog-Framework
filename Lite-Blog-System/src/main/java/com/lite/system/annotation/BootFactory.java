package com.lite.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Stranger
 * @version 1.0
 * @description: 如果想要实现自定义工厂策略，可以继承抽象工厂类并加上此注解，启动时便会采用自定义的工厂策略
 * @date 2022/8/28 12:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BootFactory {

}
