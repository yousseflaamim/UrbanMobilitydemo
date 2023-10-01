package com.example.demo.service;

import com.example.demo.exception.PaymentFailedException;
import com.example.demo.modell.User;
import com.example.demo.modell.Booking;
import com.example.demo.modell.Provider;
import com.example.demo.repostory.UserRepository;
import com.example.demo.repostory.BookingRepository;
import com.example.demo.repostory.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
 private ProviderRepository providerRepository ;
    private final BookingRepository bookingRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private  ProviderService providerService ;
    @Autowired
    private  PaymentService paymentService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public Booking chooseTransportationAndBook(String departureLocation, String arrivalLocation,
                                               LocalDateTime estimatedDepartureTime, double fare,
                                               User user, String transportationType) {
        if (user != null && user.getAccountType() == User.AccountType.USER) {
            Optional<Provider> optionalProvider = providerRepository.findByProviderName(transportationType);

            if (optionalProvider.isPresent()) {
                Provider provider = optionalProvider.get();

                if (provider.isAvailable()) {
                    Booking booking = new Booking();
                    booking.setDepartureLocation(departureLocation);
                    booking.setArrivalLocation(arrivalLocation);
                    booking.setTransportationType(transportationType);
                    booking.setEstimatedDepartureTime(estimatedDepartureTime);
                    booking.setFare(fare);
                    booking.setAccount(user);


                    provider.setAvailable(false);

                    boolean paymentSuccess = paymentService.processPayment(user, booking);

                    if (paymentSuccess) {

                        booking.setPaid(true);
                    } else {
                        throw new PaymentFailedException("Payment failed");
                    }


                    return bookingRepository.save(booking);
                } else {
                    throw new IllegalArgumentException("Transportation is currently unavailable.”);
                }
            } else {
                throw new IllegalArgumentException("Mean of transport unknown");
            }
        } else {
            throw new IllegalArgumentException(“User must be of type USER to book");
        }
    }



}
