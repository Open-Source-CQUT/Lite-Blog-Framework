package com.liteweb.exception.auth;

public class UserNotFoundException extends AuthException {

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
