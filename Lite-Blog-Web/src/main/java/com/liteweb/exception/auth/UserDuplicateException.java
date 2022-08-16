package com.liteweb.exception.auth;

public class UserDuplicateException extends Exception{

    public UserDuplicateException(){
        super();
    }

    public UserDuplicateException(String message){
        super(message);
    }
}
