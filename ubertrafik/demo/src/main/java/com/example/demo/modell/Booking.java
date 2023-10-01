package com.example.demo.modell;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureLocation;
    private String arrivalLocation;
    private String transportationType;
    private LocalDateTime estimatedDepartureTime;
    private LocalDateTime bookingDate;
    private double fare;

    public Booking(String departureLocation, String arrivalLocation, String transportationType, LocalDateTime estimatedDepartureTime, LocalDateTime bookingDate) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.transportationType = transportationType;
        this.estimatedDepartureTime = estimatedDepartureTime;
        this.bookingDate = bookingDate;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




    private boolean paid;
  
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setAccount(User user) {
        this.user = user;
    }
}

