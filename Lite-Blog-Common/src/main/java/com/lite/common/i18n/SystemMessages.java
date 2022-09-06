package com.lite.common.i18n;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 系统语言对象，用于多语言配置
 * @author Stranger
 */
@Component
public class SystemMessages {

    private static MessageSource messageSource = null;

    public SystemMessages(MessageSource messageSource) {
        SystemMessages.messageSource = messageSource;
    }

    public static String get(String key) {
        return get(key, (Object) null);
    }

    public static String get(String key, Object... args) {
        try {
            return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    public static String getStatusDefaultMsg(Integer status) {
        Assert.notNull(status, get("http.code.invalid"));

        String msg = get(String.format("http.code.%s", status));

        if (StringUtils.isBlank(msg)) {
            return get("http.code.400");
        }

        return msg;
    }

}
