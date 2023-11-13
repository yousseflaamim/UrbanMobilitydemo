package com.example.demo.controllerTest;


import com.example.demo.modell.User;
import com.example.demo.repostory.UserRepository;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUserTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;

    @Test
    public void CreateUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .param("username", "youssef")
                        .param("contactInfo", "youssef@example.com")
                        .param("paymentInfo", "193456789")
                        .param("paymentDate", "2023-09-11")
                        .param("accountType", "USER")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("youssef"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo").value("youssef@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentInfo").value("193456789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentDate").value("2023-09-11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountType").value("USER"));
    }


    @Test
    public void FindUserByUsernameTest() throws Exception {

        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setContactInfo("test@example.com");

        userRepository.save(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/find/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(username))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo").value("test@example.com"));

        userRepository.delete(mockUser);
    }



}
