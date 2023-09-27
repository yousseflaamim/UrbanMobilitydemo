package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.modell.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testCreateUserAccount() {
        UserService userService = new UserService();


        User user = userService.createUserAccount("testUser", "testContactInfo","","", User.AccountType.user);


        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("testContactInfo", user.getContactInfo());
    }
}

