package com.example.demo.serviceTest;

import com.example.demo.service.PaymentService;
import com.example.demo.modell.User;
import com.example.demo.modell.Booking;
import com.example.demo.repostory.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

public class PaymentServiceTest {
    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessPayment_SuccessfulPayment() {
        User user = new User();
        Booking booking = new Booking();

        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(null);

        boolean paymentSuccess = paymentService.processPayment(user, booking);

        assertTrue(paymentSuccess);
        assertTrue(booking.isPaid());
    }



}
