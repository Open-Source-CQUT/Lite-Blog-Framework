package com.lite.auth.exception;

import com.lite.common.exception.BaseException;

public class AuthException extends BaseException {

    public AuthException(Integer code, String msg) {
        super(code, msg);
    }
}
