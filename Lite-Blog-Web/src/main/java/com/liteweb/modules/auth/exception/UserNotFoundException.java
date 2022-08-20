package com.liteweb.modules.auth.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AuthException {

    public UserNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
