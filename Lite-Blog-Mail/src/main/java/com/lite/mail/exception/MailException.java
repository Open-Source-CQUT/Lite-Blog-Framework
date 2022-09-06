package com.lite.mail.exception;


import com.lite.common.exception.BaseException;
import com.lite.common.i18n.SystemMessages;

public class MailException extends BaseException {

    public MailException(Integer httpStatus) {
        super(httpStatus, SystemMessages.get("error.mail.send"));
    }

    public MailException(Integer httpStatus, String message) {
        super(httpStatus, message);
    }
}
