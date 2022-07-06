package com.lms.user.Controller;

import com.lms.user.Entity.user_table;
import com.lms.user.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    @Autowired
    userService service;


    @PostMapping("/newUser")
    public user_table createAccount(@RequestBody user_table user_table){
        return service.addUser(user_table);
    }

    @GetMapping("/getUser/{registrationNumber}")
    public user_table getDetails(@PathVariable int registrationNumber){
        return service.getUser(registrationNumber);
    }

    @PutMapping("/update")
    public user_table updateInfo(@RequestBody user_table user){
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/{registrationNumber}")
    public String deleteUser(@PathVariable int registrationNumber){
        return service.deleteUser(registrationNumber);
    }

}
