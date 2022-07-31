package com.lms.user.controller;

import com.lms.user.entity.User;
import com.lms.user.exception.UserAlreadyPresent;
import com.lms.user.exception.UserNotFound;
import com.lms.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserServiceImpl service;

    static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/newUser")
    public ResponseEntity<User> createAccount(@Valid @RequestBody User user) throws UserAlreadyPresent {

        logger.info("/newUser called");
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{registrationNumber}")
    public ResponseEntity<User> getDetails(@PathVariable int registrationNumber) throws UserNotFound {

        logger.info("getUser with registrationNumber "+ registrationNumber+ " called");
        User user=service.getUser(registrationNumber);
        logger.info("user get successfully");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public String updateInfo(@Valid @RequestBody User user) throws UserNotFound {

        logger.info("update method called for user "+ user.getId() + " called");
        service.updateUser(user);
        logger.info("update method run successfully for user "+ user.getId());
        return "Updated Successfully";
    }

    @DeleteMapping("/delete/{registrationNumber}")
    public String deleteUser(@PathVariable int registrationNumber) throws UserNotFound {

        logger.info("delete method called for "+ registrationNumber);
        service.deleteUser(registrationNumber);
        logger.info("user" + registrationNumber +" deleted successfully ");
        return "user deleted";
    }

    @GetMapping("/get/{id}")
    public User getUserCustomRepoController(@PathVariable int id) {

        return service.getUserFromCustomRepo(id);
    }

    @DeleteMapping("/del/{id}")
    public String deleteUserCustomRepoController(@PathVariable int id) {

        return service.deleteUserFromCustomRepo(id);
    }

    @PostMapping("/add")
    public String addUserCustomRepoController(@Valid @RequestBody User user) {

        return service.addUserFromPersistentRepo(user);
    }

    @PutMapping("/updateCustom")
    public String updateUserCustomRepoController(@Valid @RequestBody User user) {

        return service.updateUserFromPersistentRepo(user);
    }

}
