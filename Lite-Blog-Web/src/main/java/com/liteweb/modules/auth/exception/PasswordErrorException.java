package com.liteweb.modules.auth.exception;

import org.springframework.http.HttpStatus;

public class PasswordErrorException extends AuthException {

    public PasswordErrorException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

}
