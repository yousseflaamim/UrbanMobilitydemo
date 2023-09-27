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

    // إنشاء حجز جديد
    public Booking chooseTransportationAndBook(String departureLocation, String arrivalLocation,
                                               LocalDateTime estimatedDepartureTime, double fare,
                                               User user, String transportationType) {
        // التحقق من وجود المستخدم وأنه من نوع USER
        if (user != null && user.getAccountType() == User.AccountType.USER) {
            Optional<Provider> optionalProvider = providerRepository.findByProviderName(transportationType);

            if (optionalProvider.isPresent()) {
                Provider provider = optionalProvider.get();

                // تحقق من أن وسيلة النقل متاحة
                if (provider.isAvailable()) {
                    Booking booking = new Booking();
                    booking.setDepartureLocation(departureLocation);
                    booking.setArrivalLocation(arrivalLocation);
                    booking.setTransportationType(transportationType);
                    booking.setEstimatedDepartureTime(estimatedDepartureTime);
                    booking.setFare(fare);
                    booking.setAccount(user);

                    // يمكنك تعيين المزيد من الخصائص المتعلقة بالحجز هنا

                    // تحديث حالة وسيلة النقل إلى غير متاحة بعد الحجز
                    provider.setAvailable(false);

                    boolean paymentSuccess = paymentService.processPayment(user, booking);

                    if (paymentSuccess) {

                        booking.setPaid(true);
                    } else {
                        throw new PaymentFailedException("فشلت عملية الدفع");
                    }


                    return bookingRepository.save(booking);
                } else {
                    throw new IllegalArgumentException("وسيلة النقل غير متاحة حاليًا");
                }
            } else {
                throw new IllegalArgumentException("وسيلة النقل غير معروفة");
            }
        } else {
            throw new IllegalArgumentException("يجب على المستخدم أن يكون من نوع USER للحجز");
        }
    }



}
