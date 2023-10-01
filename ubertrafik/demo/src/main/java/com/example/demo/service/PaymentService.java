package com.example.demo.service;

import com.example.demo.modell.User;
import com.example.demo.modell.Booking;
import com.example.demo.modell.Payment;
import com.example.demo.repostory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private  PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentService() {
    }

    public boolean processPayment(User user, Booking booking) {
      
        boolean paymentSuccess = true;

        if (paymentSuccess) {
            booking.setPaid(true);

            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setAccount(user);;
            payment.setAmount(booking.getFare());

                paymentRepository.save(payment);
        }

        return paymentSuccess;
    }
}
