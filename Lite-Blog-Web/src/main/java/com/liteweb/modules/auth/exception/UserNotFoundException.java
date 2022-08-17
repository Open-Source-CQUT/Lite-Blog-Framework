package com.liteweb.modules.auth.exception;

public class UserNotFoundException extends AuthException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
