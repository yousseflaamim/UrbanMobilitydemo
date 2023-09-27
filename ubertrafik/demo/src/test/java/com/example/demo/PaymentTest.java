package com.example.demo;
import com.example.demo.modell.Booking;
import com.example.demo.modell.Payment;
import com.example.demo.modell.User;
import com.example.demo.service.PaymentService;
import com.example.demo.repostory.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    public void testPaymentPerformance() throws InterruptedException {
        User user = new User();
        Booking booking = new Booking();
        booking.setFare(100.0);

        int numberOfPayments = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 10 threads

        for (int i = 0; i < numberOfPayments; i++) {
            executorService.execute(() -> {
                boolean paymentSuccess = paymentService.processPayment(user, booking);
                assertTrue(paymentSuccess);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);


        // verify how much time make for s
        verify(paymentRepository, times(numberOfPayments)).save(any(Payment.class));
    }
}

