package com.liteweb.i18n;

import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

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
            return Strings.EMPTY;
        }
    }

    public static String getStatusDefaultMsg(Integer status) {
        Assert.notNull(status, get("http.code.invalid"));

        String msg = get(String.format("http.code.%s", status));

        if (Strings.isBlank(msg))
            return get("http.code.400");

        return msg;
    }

}
