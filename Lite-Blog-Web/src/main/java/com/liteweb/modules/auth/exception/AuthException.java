package com.liteweb.modules.auth.exception;

import com.liteweb.modules.common.exception.BaseException;

public class AuthException extends BaseException {

    public AuthException(Integer code, String msg) {
        super(code, msg);
    }
}
