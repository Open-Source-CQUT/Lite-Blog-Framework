package com.lite.system.core.iml;

import com.lite.system.annotation.BootFactory;
import com.lite.system.core.SystemFactoryCandidate;
import com.lite.system.core.SystemManagerFactory;
import com.lite.system.core.iml.factory.DefaultSystemManagerFactory;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/28 13:04
 */

public class DefaultFactoryCandidate implements SystemFactoryCandidate {


    /**
     * 找出启动所需的工厂类
     * @param webApplicationContext web上下文容器
     * @return 工厂类
     */
    @Override
    public SystemManagerFactory candidate(WebApplicationContext webApplicationContext) {
        return webApplicationContext.getBeansOfType(SystemManagerFactory.class).values()
                .stream()
                .filter(factory-> !Objects.isNull(factory.getClass().getAnnotation(BootFactory.class)))
                .findFirst().orElse(new DefaultSystemManagerFactory(webApplicationContext));
    }
}
