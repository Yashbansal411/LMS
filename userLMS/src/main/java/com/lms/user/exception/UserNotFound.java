package com.lms.user.exception;

import java.util.function.Supplier;

public class UserNotFound extends Exception {

    public UserNotFound(String msg) {

       super(msg);
    }

}
