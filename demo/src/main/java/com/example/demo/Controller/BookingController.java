package com.example.demo.Controller;
import com.example.demo.modell.Booking;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/book")
    public String bookTransport(@RequestBody Booking booking) {
        return bookingService.bookTransport(booking);
    }
    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        String result = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(result);
    }
}