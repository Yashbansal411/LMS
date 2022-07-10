package com.lms.user.service;

import com.lms.user.Entity.user_table;
import com.lms.user.Repository.userRepo;
import com.lms.user.Service.userService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {userService.class})
@ExtendWith(SpringExtension.class)
public class serviceTest {
    @MockBean
    private userRepo userRepo;

    @Autowired
    private userService userService;

    @Test
    void testAddUser(){
        user_table user = new user_table();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setRegistrationNumber(12345);
        user.setPassword("abc");
        when(userRepo.save((user_table) any())).thenReturn(user);

        user_table user1 = new user_table();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setRegistrationNumber(12345);
        user1.setPassword("abc");
        assertSame(user, userService.addUser(user1));
        //verify(bookRepo).save((book) any());
        verify(userRepo).save((user_table) any());
    }

    // test for update method for null value
    @Test
    void testUpdateUser(){

        when(userRepo.findById(any())).thenReturn(Optional.empty());
        user_table user1 = new user_table();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setRegistrationNumber(12345);
        user1.setPassword("abc");
        assertSame(null, userService.updateUser(user1));
        verify(userRepo).findById((Integer) any());
    }

    @Test
    void testUpdate2(){
        user_table user = new user_table();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setRegistrationNumber(12345);
        user.setPassword("abc");
        Optional<user_table> ofResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        when(userRepo.save((user_table) any())).thenReturn(user);

        user_table user1 = new user_table();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setRegistrationNumber(12345);
        user1.setPassword("abc");

        assertSame(user, userService.updateUser(user1));
        verify(userRepo).save((user_table) any());
    }

    @Test
    void testGet(){
        user_table user = new user_table();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setRegistrationNumber(12345);
        user.setPassword("abc");
        when(userRepo.findById((Integer) any())).thenReturn(Optional.of(user));

        user_table user1 = new user_table();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setRegistrationNumber(12345);
        user1.setPassword("abc");

        assertSame(user, userService.getUser(user1.getRegistrationNumber()));
        verify(userRepo).findById((Integer) any());
    }

    // test delete for data deleted
    @Test
    void testDelete(){
        user_table user = new user_table();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setRegistrationNumber(12345);
        user.setPassword("abc");
        Optional<user_table> objectResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(objectResult);
        doNothing().when(userRepo).deleteById((Integer) any());

        user_table user1 = new user_table();
        user1.setFirstName("first");
        user1.setLastName("last");
        user1.setEmail("first@gmail.com");
        user1.setRegistrationNumber(1234);
        user1.setPassword("abc");

        assertEquals("deleted successfully", userService.deleteUser(1234));
        verify(userRepo).deleteById((Integer) any());
        verify(userRepo).findById((Integer) any());
    }

    // test case when data not present
    @Test
    void testDelete2(){
        user_table user = new user_table();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setRegistrationNumber(12345);
        user.setPassword("abc");
        Optional<user_table> emptyResult = Optional.empty();
        when(userRepo.findById((Integer) any())).thenReturn(emptyResult);
        assertEquals("object not found",userService.deleteUser(1234));
        verify(userRepo).findById((Integer) any());
    }
}
