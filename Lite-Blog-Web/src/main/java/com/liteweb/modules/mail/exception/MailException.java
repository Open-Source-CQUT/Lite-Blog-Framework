package com.liteweb.modules.mail.exception;

import com.liteweb.i18n.LocalMessages;
import com.liteweb.modules.common.exception.BaseException;

public class MailException extends BaseException {

    public MailException(Integer httpStatus) {
        super(httpStatus, LocalMessages.get("error.mail.send"));
    }

    public MailException(Integer httpStatus, String message) {
        super(httpStatus, message);
    }
}
