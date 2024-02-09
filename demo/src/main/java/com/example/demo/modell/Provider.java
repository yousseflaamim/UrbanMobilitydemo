package com.example.demo.modell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Provider  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactInfo;
    private String providerName;
    @Column(name = "type_transportation")
    private String typeTransportation;
    private boolean available;
    private double compensation;
    private double promotionalDiscount;
    @OneToMany(mappedBy = "provider")
    private List<Booking> bookings;
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean b) {
    }
}