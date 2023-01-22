package com.examserver.examserver.helper;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("User with the username is already there in DB !");
    }

    public UserFoundException(String msg) { 
        super(msg); 
    }
}
