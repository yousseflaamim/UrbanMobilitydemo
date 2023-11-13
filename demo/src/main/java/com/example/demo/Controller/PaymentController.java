package com.example.demo.Controller;




import com.example.demo.modell.Booking;
import com.example.demo.modell.Payment;
import com.example.demo.modell.User;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //  process payment 4 a booking
    @PostMapping("/process")
    public String processPayment(@RequestBody Payment paymentRequest) {

        User user = paymentRequest. getUser();
        Booking booking = paymentRequest. getBooking();

        if (paymentService.processPayment(user, booking)) {
            return "The payment was completed successfully!";
        } else {
            return "Payment failed. Please try again.";
        }
    }



}