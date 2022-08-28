package com.lite.system.core.iml.factory;

import com.lite.system.core.SystemManager;
import com.lite.system.core.SystemManagerFactory;
import com.lite.system.core.iml.DefaultSystemManager;
import lombok.Getter;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: SystemManager抽象工厂
 * @date 2022/8/27 15:23
 */
@Getter
public abstract class AbstractSystemManagerFactory implements SystemManagerFactory {

    /**
     * 整个应用中可以存在多SystemManager个实例
     * 但是必须只能有一个SystemManager在应用启动时被采用
     */
    public SystemManager bootManager;

    public SystemManager defaultManager;

    public List<SystemManager>  systemManagerList;

    @Override
    public void loadFactory(WebApplicationContext webApplicationContext) {

        this.systemManagerList = new ArrayList<>(
                webApplicationContext.getBeansOfType(SystemManager.class).values());

        this.defaultManager = webApplicationContext.getBean(DefaultSystemManager.class);

    }

    /**
     * 子类必须实现如何从候选者中选择出目标
     * @param systemManagerList manager集合
     * @return 目标manager
     */
    @Override
    public abstract SystemManager judgeCandidate(List<SystemManager> systemManagerList);


    @Override
    public abstract void start();

}
