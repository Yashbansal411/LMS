package com.lms.user.service;

import com.lms.user.entity.User;
import com.lms.user.exception.UserAlreadyPresent;
import com.lms.user.exception.UserNotFound;
import com.lms.user.repository.UserPersistent;
import com.lms.user.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userrepo;

    @Autowired
    private UserPersistent userPersistent;

    static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /*
        This method intend to add new user in database
        However note that user id should be unique
        @Param user is the object
     */
    public User addUser(User user) throws UserAlreadyPresent {
        logger.info("add user called successfully for user "+ user.getId());
        // code for getting user object form database
        User obj = userrepo.findById(user.getId()).orElse(null);
        /* code for checking is object if null and save object in database */
        if (obj == null)
            return userrepo.save(user);
        else {
            // code for returning exception
            throw new UserAlreadyPresent("User Already present");
        }
    }
    /*
    This method intend to update user in database
    However note that user must be present in database
    @Param user is the object
     */
    public User updateUser(User user) throws UserNotFound {
        logger.info("update user function called successfully for user id "+ user.getId());
        // code for getting user object from database or throw exception
        User currentUser = userrepo.findById(user.getId()).orElseThrow(
                () -> new UserNotFound("no registered user for update"));

        logger.info("update function run successfully for id "+user.getId());
        // code to save user object in database
        return userrepo.save(user);
    }
    /*
        This method intend to get user from database
        However note that user must be present in database
        @Param number is unique id of the user
     */
    public User getUser(int number) throws UserNotFound {
        logger.info("get user function called successfully for id "+ number);
        // code to get user object from database or throw exception
        return userrepo.findById(number).orElseThrow(()->new UserNotFound("no registered user for update"));
    }
    /*
        This method intend to delete user from database
        However note that user must be present in database
        @Param registrationNumber is unique user id
     */
    public String deleteUser(int registrationNumber) throws UserNotFound {
        logger.info("delete user function called successfully for id "+registrationNumber);
        // code for getting user object from database or throw exception
        User obj = userrepo.findById(registrationNumber).orElseThrow(()-> new UserNotFound("no registered user for update"));
        // code for delete object from database
        userrepo.deleteById(obj.getId());
        logger.info("user deleted successfully for id "+ registrationNumber);
        return "deleted successfully";
    }
    /*
        This method intend to get user from database using custom repository
        @Param id is unique user id
     */
    public User getUserFromCustomRepo(int id) {
        // code for getting object from custom repository
        return userPersistent.getUser(id);
    }
    /*
        This method intend to delete user using custom repo
        @Param id is unique user id
     */
    public String deleteUserFromCustomRepo(int id) {
        // code for deleting object from database using custom repository
        return userPersistent.deleteQuery(id);
    }
    /*
       This method intend to add user in database using custom repository
       @Param user is user object
     */
    public String addUserFromPersistentRepo(User user) {
        // code to insert object in database using custom repo
        userPersistent.insertQuery(user);
        return "User successfully";
    }

    /*
        This method intend to update user using custom repository
        @Param user is User class object
    */
    public String updateUserFromPersistentRepo(User user) {

        // code to update object using custom repo
        return userPersistent.updateQuery(user);
    }
}

