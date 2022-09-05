package com.lite.api.lua;

import com.alibaba.fastjson2.JSONObject;
import com.lite.common.entity.RedisEvalRes;
import com.lite.common.serializer.RedisCache;
import com.lite.common.serializer.RedisJsonScript;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.*;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/5 16:18
 */
@Slf4j
@SpringBootTest
public class RedisLuaTest {

    @Autowired
    RedisCache redisCache;

    @Test
    void scripTest() {
        Resource resource = new ClassPathResource("lua/ipLimit.lua");

        RedisEvalRes evalRes = redisCache.execute(
                RedisJsonScript.of(resource),
                Collections.singletonList("blog"),
                1,2);

        log.info(evalRes.getResult(String.class));

    }

}
