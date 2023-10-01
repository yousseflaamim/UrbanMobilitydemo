package com.example.demo.modell;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String trackingNumber;
    private LocalDate paymentDate;
    private double amount;


    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Payment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccount(User user) {
    }
}
