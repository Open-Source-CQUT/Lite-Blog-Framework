package com.lite.system.core.iml.factory;

import com.lite.system.annotation.BootManager;
import com.lite.system.core.SystemManager;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import java.util.Objects;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 15:49
 */
public class DefaultSystemManagerFactory extends AbstractSystemManagerFactory {


    public WebApplicationContext context;

    public DefaultSystemManagerFactory(WebApplicationContext context) {
        this.context = context;
        this.loadFactory(context);
        this.bootManager = this.judgeCandidate(this.systemManagerList);
    }

    @Override
    public SystemManager judgeCandidate(List<SystemManager> systemManagerList) {

        return systemManagerList
                .stream()
                .filter(systemManager -> !Objects.isNull(systemManager.getClass().getAnnotation(BootManager.class)))
                .findFirst().orElse(this.getDefaultManager());
    }

    @Override
    public void start() {
        this.getBootManager().loadSystemInfo(this.context);
    }

}
