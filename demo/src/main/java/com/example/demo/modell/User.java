package com.example.demo.modell;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class User {
    public static User.AccountType AccountType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String contactInfo;
    private String paymentInfo;

    public User(String username, String contactInfo, String s, String s1, User.AccountType user) {
        this.username = username;
        this.contactInfo = contactInfo;
    }
    private String paymentDate;
    @OneToMany(mappedBy = "user")
    private List<Booking> activeBookings;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    public enum AccountType {
        ADMIN,
        USER, GUEST,
    }
}
//youssef laamim