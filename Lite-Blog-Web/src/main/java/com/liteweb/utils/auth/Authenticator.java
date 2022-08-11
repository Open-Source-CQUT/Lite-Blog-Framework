package com.liteweb.utils.auth;

import com.alibaba.fastjson.JSON;
import com.liteweb.entity.auth.User;
import com.liteweb.utils.serializer.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Authenticator {


    @Autowired
    RedisCache redisCache;


    public Boolean authenticate(String token){

        try {
            //读取payload
            String payload = JwtUtil.parseJWT(token).getSubject();

            //JSON转换
            User user = JSON.parseObject(payload,User.class);

            //读取Redis缓存中的用户
            if (Objects.isNull(redisCache.getCacheObject(user.getMail())))
                return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
