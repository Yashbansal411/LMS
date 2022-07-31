package com.lms.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.user.entity.User;
import com.lms.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
public class ControllerTest {
    @Autowired
    UserController controllerObj;

    @MockBean
    UserServiceImpl service;

    // get user testing
    @Test
    void getUserTesting() throws Exception{
        User user = new User();
        user.setFirstName("Yash");
        user.setLastName("Bansal");
        user.setEmail("yashbansal@gmail.com");
        user.setId(12345);
        //user.setPassword();
        when(service.getUser(anyInt())).thenReturn(user);
        // mocking url hit
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUser/{registrationNumber}",1);
        MockMvcBuilders.standaloneSetup(controllerObj).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
   // hamcrest --> for testing
    @Test
    void addUser() throws Exception{
        User user = new User();
        user.setFirstName("Yash");
        user.setLastName("Bansal");
        user.setEmail("yashbansal@gmail.com");
        user.setId(12345);
        when(service.addUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setFirstName("Yash");
        user1.setLastName("Bansal");
        user1.setEmail("yashbansal@gmail.com");
        user1.setId(12345);
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(controllerObj)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));

    }

    @Test
    void testDeleteUser() throws Exception{
        when(service.deleteUser(anyInt())).thenReturn("deleted successfully");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{registrationNumber}", 1);
        MockMvcBuilders.standaloneSetup(controllerObj)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted successfully"));
    }

    @Test
    void testUpdateUser() throws Exception{
        User user = new User();
        user.setFirstName("Yash");
        user.setLastName("Bansal");
        user.setEmail("yashbansal@gmail.com");
        user.setId(12345);
        when(service.updateUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setFirstName("Yash");
        user1.setLastName("Bansal");
        user1.setEmail("yashbansal@gmail.com");
        user1.setId(12345);
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(controllerObj)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

}

