package com.lite.common.serializer;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/5 19:06
 */
public interface RedisJsonScript<T> extends RedisScript<T> {

    static RedisScript<JSONObject> of(Resource resource){
        return RedisScript.of(resource,JSONObject.class);
    }
}
