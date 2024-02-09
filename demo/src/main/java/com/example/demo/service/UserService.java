package com.example.demo.service;
import com.example.demo.modell.User;
import com.example.demo.repostory.UserRepository;
import com.example.demo.repostory.BookingRepository;
import com.example.demo.repostory.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private PaymentService paymentService;
    public User createUserAccount(String username, String contactInfo, String paymentInfo,
                                  String paymentDate, User.AccountType accountType) {
        User account = new User();
        account.setUsername(username);
        account.setContactInfo(contactInfo);
        account.setPaymentInfo(paymentInfo);
        account.setPaymentDate(paymentDate);
        account.setAccountType(accountType);
        return userRepository.save(account);
    }
   public User findUserByUsername(String username) {
        return (User) userRepository.findByUsername(username)
               .orElseThrow(() -> new EntityNotFoundException("User not found by name: " + username));
    }
    public void deleteUserByUsername(String username) {
        User user = findUserByUsername(username);
        userRepository.delete(user);
    }
    public User updateUser(String username, String contactInfo, String paymentInfo) {
        User user = findUserByUsername(username);
        user.setContactInfo(contactInfo);
        user.setPaymentInfo(paymentInfo);
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}