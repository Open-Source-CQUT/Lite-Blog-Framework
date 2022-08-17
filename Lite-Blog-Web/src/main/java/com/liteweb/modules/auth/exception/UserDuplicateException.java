package com.liteweb.modules.auth.exception;

public class UserDuplicateException extends Exception {

    public UserDuplicateException() {
        super();
    }

    public UserDuplicateException(String message) {
        super(message);
    }
}
