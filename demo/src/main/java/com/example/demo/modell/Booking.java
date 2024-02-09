package com.example.demo.modell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "arrival_location")
    private String arrivalLocation;
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    @Column(name = "departure_location")
    private String departureLocation;
    @Column(name = "estimated_departure_time")
    private LocalDateTime estimatedDepartureTime;
    @Column(name = "fare")
    private double fare;
    @Column(name = "paid")
    private boolean paid;
    @Column(name = "transportation_type")
    private String transportationType;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User account;
    public Booking(String departureLocation, String arrivalLocation,
                   String TypeTransportation, LocalDateTime estimatedDepartureTime,
                   LocalDateTime bookingDate) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.transportationType = TypeTransportation;
        this.estimatedDepartureTime = estimatedDepartureTime;
        this.bookingDate = bookingDate;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
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