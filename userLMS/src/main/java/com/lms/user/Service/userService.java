package com.lms.user.Service;

import com.lms.user.Entity.user_table;
import com.lms.user.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private userRepo userrepo;
    public user_table addUser(user_table user_table){
        return userrepo.save(user_table);

    }
    public user_table updateUser(user_table user){
        user_table  current_user = userrepo.findById(user.getRegistrationNumber()).orElse(null);
        if(current_user==null)
            return null;
        return userrepo.save(user);
    }

    public user_table getUser(int number){
        return userrepo.findById(number).orElse(null);
    }

    public String deleteUser(int registrationNumber){
        user_table obj = userrepo.findById(registrationNumber).orElse(null);
        if(obj==null)
            return "object not found";
        userrepo.deleteById(obj.getRegistrationNumber());
        return "deleted successfully";
    }

}
