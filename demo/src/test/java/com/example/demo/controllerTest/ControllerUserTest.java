package com.example.demo.controllerTest;


import com.example.demo.modell.User;

import com.example.demo.repostory.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void CreateUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .param("username", "uniqueUsername") // Ensure a unique username
                        .param("contactInfo", "youssef@example.com")
                        .param("paymentInfo", "193456789")
                        .param("paymentDate", "2023-09-11")
                        .param("accountType", "USER")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("uniqueUsername"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo").value("youssef@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentInfo").value("193456789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentDate").value("2023-09-11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountType").value("USER"));
    }

    @Test
    public void FindUserByUsernameTest() throws Exception {
        String username = "youssef";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setContactInfo("youssef@example.com");
        userRepository.save(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/find/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(username))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo").value("youssef@example.com"));

        userRepository.delete(mockUser);
    }
}
