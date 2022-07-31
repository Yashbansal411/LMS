package com.lms.user.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyPresent extends Exception{

    public UserAlreadyPresent(String msg) {

        super(msg);
    }
}
