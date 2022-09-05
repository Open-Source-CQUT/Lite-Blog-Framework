package com.lite.common.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/5 19:17
 */
@Data
public class RedisEvalRes {

    private static final String RES_FLAG = "result";
    private JSONObject result;

    public <T> T getResult(Class<T> tClass){
        return result.getObject(RES_FLAG,tClass);
    }

    public RedisEvalRes(JSONObject result) {
        this.result = result;
    }
}
