package com.lite.system.core;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/28 12:59
 */

public interface SystemFactoryCandidate {

    SystemManagerFactory candidate(WebApplicationContext webApplicationContext);

}
