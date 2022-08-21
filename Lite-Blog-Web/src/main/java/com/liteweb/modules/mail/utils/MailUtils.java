package com.liteweb.modules.mail.utils;

public class MailUtils {

    public static final String KEY = "Auth-Mail-";

    public static final String CODE_VARIABLE = "authCode";

    public static final String TEMPLATE_PATH = "mail";

    public static String getMailRedisKey(String mail) {
        return KEY + mail;
    }
}
