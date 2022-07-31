package com.lms.user.service;

import com.lms.user.entity.User;
import com.lms.user.exception.UserAlreadyPresent;
import com.lms.user.exception.UserNotFound;
import com.lms.user.repository.UserPersistent;
import com.lms.user.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
    @MockBean
    private UserRepo userRepo;

    @MockBean
    private UserPersistent userPersistent;
    @Autowired
    private UserServiceImpl userServiceImpl;

    /*
        This method intend is to check if user service enable to add user object to database or not,
        by assuming that other functionalities of the method is working fine
     */
    @Test
    void testAddUser() throws UserAlreadyPresent {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(12345);
        user.setPassword("abc");
        when(userRepo.findById(anyInt())).thenReturn(Optional.empty());
        when(userRepo.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setId(12345);
        user1.setPassword("abc");
        // junit assert
        // assertSame(user, userService.addUser(user1));
        // hamcrest assert
        assertThat(user, equalTo(userServiceImpl.addUser(user1)));
        verify(userRepo).save((User) any());
    }
/*
    This method intend is to check if user service unable to add user object to database if same user id present,
        by assuming that other functionalities of the method is working fine
 */
    @Test
    void testAddUserException() throws UserAlreadyPresent {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(12345);
        user.setPassword("abc");
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyPresent.class, () -> userServiceImpl.addUser(user));
    }

    /*
        This method intend is to check if user service able to throw exception if userid is not present in database,
        by making sure that other functionalities of the method is working fine
     */
    @Test
    void testUpdateUser() throws UserNotFound {
        when(userRepo.findById(any())).thenReturn(Optional.empty());
        User user1 = new User();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setId(12345);
        user1.setPassword("abc");
        Optional<User> ofResult = Optional.empty();
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(UserNotFound.class, () -> userServiceImpl.updateUser(user1));
    }
    /*

     */
    @Test
    void testUpdate2() throws UserNotFound {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(12345);
        user.setPassword("abc");
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        when(userRepo.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setId(12345);
        user1.setPassword("abc");
        assertThat(user, equalTo(userServiceImpl.updateUser(user1)));
        verify(userRepo).save((User) any());
    }
    /*
        This method intend is to test if user details is fetching correctly from database
     */
    @Test
    void testGet() throws UserNotFound {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(12345);
        user.setPassword("abc");
        when(userRepo.findById((Integer) any())).thenReturn(Optional.of(user));

        User user1 = new User();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setId(12345);
        user1.setPassword("abc");

        //assertSame(user, userService.getUser(user1.getRegistrationNumber()));
        assertThat(user, equalTo(userServiceImpl.getUser(user1.getId())));
        verify(userRepo).findById((Integer) any());
    }

    @Test
    void testGetToCheckException() throws UserNotFound {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(12345);
        user.setPassword("abc");
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class, () -> userServiceImpl.getUser(1));
    }

    // test delete for data deleted
    @Test
    void testDelete() throws UserNotFound {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setId(12345);
        user.setPassword("abc");
        Optional<User> objectResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(objectResult);
        doNothing().when(userRepo).deleteById((Integer) any());

        User user1 = new User();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setId(1234);
        user1.setPassword("abc");

        //assertEquals("deleted successfully", userService.deleteUser(1234));
        assertThat("deleted successfully", equalTo(userServiceImpl.deleteUser(1234)));
        verify(userRepo).deleteById((Integer) any());
        verify(userRepo).findById((Integer) any());
    }

    // test case when data not present
    @Test
    void testDelete2() throws UserNotFound {
        Optional<User> emptyResult = Optional.empty();
        when(userRepo.findById((Integer) any())).thenReturn(emptyResult);
        //assertEquals("object not found",userService.deleteUser(1234));
        assertThrows(UserNotFound.class, () -> userServiceImpl.deleteUser(1234));
        verify(userRepo).findById((Integer) any());
    }


}
