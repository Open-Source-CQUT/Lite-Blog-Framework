package com.lite.system.annotation;

import com.lite.system.entity.PermissionId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在类上时，代表该类下的所有接口方法的默认权限即类权限
 * 在方法上时，即代表覆盖默认权限
 * @author Stranger
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 访问接口所需的最小权限
     */

    PermissionId Min() default PermissionId.USER;
}
