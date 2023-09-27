package com.example.demo.Controller;
import com.example.demo.modell.Booking;
import com.example.demo.modell.User;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // creatbookig
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking ,User user) {
        try {

            Booking bookings = bookingService.chooseTransportationAndBook(
                    booking.getDepartureLocation(),
                    booking.getArrivalLocation(),
                    booking.getEstimatedDepartureTime(),
                    booking.getFare(),
                    user,
                    booking.getTransportationType()
            );
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
