package com.lite.auth.exception;

import org.springframework.http.HttpStatus;

public class UserDuplicateException extends AuthException {

    public UserDuplicateException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
