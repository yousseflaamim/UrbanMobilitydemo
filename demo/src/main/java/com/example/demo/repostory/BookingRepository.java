package com.example.demo.repostory;
import com.example.demo.modell.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<Booking, Long> {
}