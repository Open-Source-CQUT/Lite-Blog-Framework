package com.lite.system;

import com.lite.common.i18n.SystemMessages;
import com.lite.system.config.SystemConfig;
import com.lite.system.core.RunnableFactory;
import com.lite.system.core.iml.DefaultFactoryCandidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.WebApplicationContext;


@Slf4j
@Component
public class SystemApplicationRunner implements ApplicationRunner {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    SystemConfig systemConfig;

    /**
     * 异步执行 防止阻塞主线程任务
     *
     * @param args incoming application arguments
     */
    @Async
    @Override
    public void run(ApplicationArguments args) {
        //任务开始
        startFactory(() -> new DefaultFactoryCandidate().candidate(webApplicationContext).start());
    }


    //因为这么一点代码就用aop太不划算
    public void startFactory(RunnableFactory runnableFactory) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("success.sys.scan"));
            log.info(SystemMessages.get("arrowhead"));
        }
        runnableFactory.start();
        stopWatch.stop();
        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("arrowhead"));
            log.info(SystemMessages.get("success.sys.allCompleted"));
            log.info(SystemMessages.get("success.sys.timeConsume", stopWatch.getTotalTimeSeconds()));
        }
    }

}
