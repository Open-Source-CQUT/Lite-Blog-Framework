package com.lite.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Stranger
 * @version 1.0
 * @description: 如果想要自定义扫描策略建议实现SystemManager接口并加上此注解，
 *               如果想要实现自定义数据处理建议继承抽象AbstractSystemManager类并加上此注解
 * @date 2022/8/27 15:44
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BootManager {

}
