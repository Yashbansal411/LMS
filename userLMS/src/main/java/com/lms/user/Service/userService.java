package com.lms.user.service;

import com.lms.user.entity.User;
import com.lms.user.exception.UserAlreadyPresent;
import com.lms.user.exception.UserNotFound;

public interface UserService {

    public User addUser(User user) throws UserAlreadyPresent;

    public User updateUser(User user) throws UserNotFound;

    public User getUser(int number) throws UserNotFound;

    public String deleteUser(int registrationNumber) throws UserNotFound;
}
