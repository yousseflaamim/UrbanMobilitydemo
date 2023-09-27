package com.example.demo.modell;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String contactInfo;
    private String paymentInfo;

    public User(String username, String contactInfo) {
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
        USER,

    }
}

