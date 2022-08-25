package com.lite.common.i18n;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Locale;

@Component
public class LocalMessages {

    private static MessageSource messageSource = null;

    public LocalMessages(MessageSource messageSource) {
        LocalMessages.messageSource = messageSource;
    }

    public static String get(String key) {
        return get(key, LocaleContextHolder.getLocale());
    }

    public static String get(String key, Locale local) {
        try {
            return messageSource.getMessage(key, null, local);
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    public static String getStatusDefaultMsg(Integer status) {
        Assert.notNull(status, get("http.code.invalid"));

        String msg = get(String.format("http.code.%s", status));

        if (StringUtils.isBlank(msg))
            return get("http.code.400");

        return msg;
    }

}
