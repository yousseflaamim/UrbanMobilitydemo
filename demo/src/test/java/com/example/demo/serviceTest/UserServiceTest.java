package com.example.demo.serviceTest;

import com.example.demo.modell.User;

import com.example.demo.repostory.UserRepository;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserTest() {
        String username = "uniqueUsername"; // Ensure a unique username
        String contactInfo = "youssef@example.com";
        String paymentInfo = "1234-5678-9012-3456";
        String paymentDate = "01/25";
        User.AccountType accountType = User.AccountType.USER;
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User user = userService.createUserAccount(username, contactInfo, paymentInfo, paymentDate, accountType);
        assertEquals(username, user.getUsername());
        assertEquals(contactInfo, user.getContactInfo());
    }

    @Test
    public void findUserByUsernameTest() {
        String username = "youssef";
        User mockUser = new User();
        mockUser.setContactInfo("youssef@example.com");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        User foundUser = userService.findUserByUsername(username);
        assertEquals("youssef@example.com", foundUser.getContactInfo());
    }

    @Test
    public void findUserByUsername_UserNotFoundTest() {
        String username = "nonexistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.findUserByUsername(username);
        });
    }
}
