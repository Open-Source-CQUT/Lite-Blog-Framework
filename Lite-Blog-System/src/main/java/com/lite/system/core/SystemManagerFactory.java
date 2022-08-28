package com.lite.system.core;

import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 15:28
 */
public interface SystemManagerFactory  extends RunnableFactory{

    /**
     * 初始化工厂信息
     *
     * @param webApplicationContext web上下文容器
     */
    void loadFactory(WebApplicationContext webApplicationContext);

    /**
     * 从候选manager选出一个manager
     * @param systemManagerList manager集合
     * @return 被选择出的manager
     */
    SystemManager judgeCandidate(List<SystemManager> systemManagerList);

    /**
     * 开工
     */
    void start();
}
