package com.liteweb.exception.auth;

import lombok.Data;

public class PasswordErrorException extends AuthException {

    public PasswordErrorException(){
        super();
    }

    public PasswordErrorException(String message){
        super(message);
    }

}
