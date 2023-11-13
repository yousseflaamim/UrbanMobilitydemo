package com.example.demo.service;


import com.example.demo.modell.Booking;
import com.example.demo.repostory.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ProviderService providerService;

    public String bookTransport(Booking booking) {

        if (providerService.isTransportAvailable(booking.getTransportationType())) {

            bookingRepository. save(booking);
            return "Transportation has been booked successfully!";
        } else {
            // If not available, return a message saying so
            return "Sorry, transportation is currently unavailable.";
        }
    }

    public String cancelBooking(Long bookingId) {

        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElse(null);

        if (existingBooking != null) {

            bookingRepository. delete(existingBooking);

            return "Your reservation has been successfully cancelled!";
        } else {
            return "The reservation does not exist.";
        }
    }
}