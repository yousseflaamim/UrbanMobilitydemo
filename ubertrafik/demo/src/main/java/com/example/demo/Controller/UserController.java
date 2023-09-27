package com.example.demo.Controller;

import com.example.demo.modell.Provider;
import com.example.demo.modell.User;
import com.example.demo.service.ProviderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    private ProviderService providerService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUserAccount(@RequestParam String username,
                                                  @RequestParam String contactInfo,
                                                  @RequestParam String paymentInfo,
                                                  @RequestParam String paymentDate,
                                                  @RequestParam User.AccountType accountType) {
        User user = userService.createUserAccount(username, contactInfo, paymentInfo, paymentDate, accountType);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/find/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }




}
