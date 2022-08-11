package com.liteweb.exception.auth;

public class AuthException extends Exception{

    public AuthException(){
        super();
    }

    public AuthException(String msg){
        super(msg);
    }
}
