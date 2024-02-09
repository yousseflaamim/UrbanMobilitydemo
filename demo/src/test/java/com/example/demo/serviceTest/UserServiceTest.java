package com.example.demo.serviceTest;
import com.example.demo.Controller.UserController;
import com.example.demo.repostory.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.modell.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest
public class UserServiceTest {
    @Autowired
     UserService userService;
    @Mock
     UserRepository userRepository;
    @Test
    public void CreateUserTest() {
        String username = "testUser";
        String contactInfo = "youssef@example.com";
        String paymentInfo = "1234-5678-9012-3456";
        String paymentDate = "01/25";
        User.AccountType accountType = User.AccountType.USER;
        User user = userService.createUserAccount(username, contactInfo, paymentInfo, paymentDate, accountType);
        assert user != null;
        assert user.getId() != null;
    }
    @Test
    public void FindUserByUsernameTest() {
        String username = "testUser";
        User mockUser = new User();
        UserService userServiceMock = mock(UserService.class);
        when(userServiceMock.findUserByUsername(username)).thenReturn(mockUser);
        UserController controller = new UserController(userServiceMock);
        ResponseEntity<User> response = controller.findUserByUsername(username);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userServiceMock, times(1)).findUserByUsername(username);
    }
    @Test
    public void FindUserByUsername_UserNotFoundTest() {
        when(userRepository.findByUsername("nonexistentUser")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            userService.findUserByUsername("nonexistentUser");
        });
    }
}