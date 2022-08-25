package com.lite.mail.utils;


import com.lite.common.utils.JwtUtil;

public class MailUtils {

    public static final String KEY = "Auth-Mail-";

    public static final String CODE_VARIABLE = "authCode";

    public static final String TEMPLATE_PATH = "mail";

    public static String getMailRedisKey(String mail) {
        return KEY + mail;
    }

    public static String generateAuthCode() {
        return JwtUtil.getUUID().substring(0, 6);
    }
}
