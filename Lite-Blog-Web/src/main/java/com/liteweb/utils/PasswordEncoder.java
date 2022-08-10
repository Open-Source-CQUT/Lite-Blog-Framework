package com.liteweb.utils;

import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    public static String enCode(String password){
        return DigestUtils.sha1DigestAsHex(password);
    }
}
