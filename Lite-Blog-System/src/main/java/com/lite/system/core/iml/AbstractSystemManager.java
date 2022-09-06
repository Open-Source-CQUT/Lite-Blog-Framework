package com.lite.system.core.iml;

import com.lite.common.i18n.SystemMessages;
import com.lite.system.annotation.Permission;
import com.lite.system.config.SystemConfig;
import com.lite.system.entity.PermissionId;
import com.lite.system.entity.SystemApi;
import com.lite.system.entity.SystemController;
import com.lite.system.entity.SystemEntity;
import com.lite.system.core.SystemManager;
import com.lite.system.utils.SystemStringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 抽象类SystemAbstractManager,此类用于初始化系统信息,获取到信息后如何操作取决于子类如何实现
 */
@Slf4j
@Data
public abstract class AbstractSystemManager implements SystemManager {

    /**
     * 接口处理器map
     */
    Map<RequestMappingInfo, HandlerMethod> handlerMethodMap;

    /**
     * 路径映射信息集合
     */
    List<RequestMappingInfo> requestMappingInfoList;

    /**
     * 接口处理器集合
     */
    List<HandlerMethod> handlerMethodList;

    //配置类
    SystemConfig systemConfig;
    /**
     * 刷新系统上下文信息
     */
    public void refreshContextInfo(WebApplicationContext webApplicationContext) {
        this.handlerMethodMap = webApplicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        this.requestMappingInfoList = this.handlerMethodMap.keySet().stream().distinct().collect(Collectors.toList());
        this.handlerMethodList = this.handlerMethodMap.values().stream().distinct().collect(Collectors.toList());
        this.systemConfig = webApplicationContext.getBean(SystemConfig.class);
    }

    /**
     * 加载系统信息
     */
    @Override
    public abstract void loadSystemInfo(WebApplicationContext webApplicationContext);

    /**
     * 刷新系统controller信息
     */
    public List<SystemController> loadSystemControllerInfo() {
        Assert.notNull(handlerMethodList, SystemMessages.get("error.sys.ini"));
        return handlerMethodList.stream().map(HandlerMethod::getBeanType).distinct().map(handlerClass -> {
                    //获取包名
                    String packageName = handlerClass.getPackage().getName();

                    //获取类名
                    String clazzName = handlerClass.getSimpleName();

                    //获取RequestMapping注解
                    RequestMapping annotation = handlerClass.getAnnotation(RequestMapping.class);

                    //获取Permission
                    Permission permission = handlerClass.getAnnotation(Permission.class);

                    Integer permissionId = Objects.isNull(permission) ? PermissionId.DEFAULT.val() : permission.Min().val();

                    //url拼接
                    String mappingUrl = annotation == null ? Strings.EMPTY : SystemStringUtils.concatWithComma(annotation.value());

                    //组装对象
                    return new SystemController(mappingUrl, clazzName, packageName, handlerClass.getName(),permissionId);

                })
                .sorted(Comparator.comparing(SystemEntity::getFullName))
                .collect(Collectors.toList());
    }

    /**
     * 刷新系统API信息
     */
    public List<SystemApi> loadSystemApiInfo() {
        Assert.notNull(handlerMethodMap, SystemMessages.get("error.sys.ini"));
        return requestMappingInfoList.stream().distinct()
                .map(handlerMethodInfo -> {
                    //断言不能为空
                    Assert.notNull(handlerMethodInfo.getPathPatternsCondition());

                    List<String> urlList = handlerMethodInfo
                            .getPathPatternsCondition()
                            .getPatterns()
                            .stream()
                            .map(PathPattern::getPatternString)
                            .collect(Collectors.toList());

                    Assert.notEmpty(urlList, SystemMessages.get("error.sys.api.emptyUrl"));

                    String wrapMethod = SystemStringUtils.concatWithComma(handlerMethodInfo
                            .getMethodsCondition()
                            .getMethods()
                            .stream()
                            .map(Enum::name));

                    //对于的接口处理方法
                    HandlerMethod handlerMethod = this.handlerMethodMap.get(handlerMethodInfo);

                    //ctrl上的注解
                    Permission ctrlPermission = handlerMethod.getBean().getClass().getAnnotation(Permission.class);
                    //api上的注解
                    Permission apiPermission = handlerMethod.getMethod().getAnnotation(Permission.class);

                    //方法名
                    String name = handlerMethod.getMethod().getName();
                    //全限定方法名
                    String fullName = SystemStringUtils.removeBracketContent(handlerMethod.toString());

                    //权限ID
                    Integer apiPermissionId = Objects.isNull(apiPermission) ?
                            Objects.isNull(ctrlPermission) ? PermissionId.DEFAULT.val() : ctrlPermission.Min().val() :
                            apiPermission.Min().val();

                    //默认采用ctrl上的权限
                    return urlList.stream()
                            .map(url-> new SystemApi(name, fullName+url, systemConfig.getContextPath()+url, wrapMethod,apiPermissionId))
                            .collect(Collectors.toList());

                })
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(SystemEntity::getFullName))
                .collect(Collectors.toList());
    }

}
