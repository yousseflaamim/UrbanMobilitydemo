package com.example.demo.serviceTest;

import com.example.demo.modell.Booking;
import com.example.demo.repostory.BookingRepository;
import com.example.demo.service.BookingService;
import com.example.demo.service.ProviderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookingServiceTest {

    @Mock
    private ProviderService providerService;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void bookTransportWhenTransportAvailable() {
        Booking booking = new Booking();
        booking.setTransportationType("car");

        when(providerService.isTransportAvailable("car")).thenReturn(true);

        String result = bookingService.bookTransport(booking);

        assertEquals("Your transportation has been booked successfully!!!", result);

        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void bookTransportWhenTransportNotAvailable() {
        Booking booking = new Booking();
        booking.setTransportationType("train");

        when(providerService.isTransportAvailable("train")).thenReturn(false);

        String result = bookingService.bookTransport(booking);

        assertEquals("Sorry, transportation is currently unavailable.", result);

        verify(bookingRepository, times(0)).save(booking);
    }
    @Test
    public void cancelBookingSuccess() {
        // إعداد الحجز الموجود
        Booking existingBooking = new Booking();
        existingBooking.setId(1L);
        existingBooking.setTransportationType("Car");

        when(bookingRepository.findById(existingBooking.getId())).thenReturn(java.util.Optional.of(existingBooking));

        String result = bookingService.cancelBooking(existingBooking.getId());

        verify(bookingRepository, times(1)).delete(existingBooking);

        assertEquals("Your reservation has been successfully cancelled!", result);
    }
    @Test
    public void cancelBookingBookingNotFound() {
        Long nonExistingBookingId = 999L;

        when(bookingRepository.findById(nonExistingBookingId)).thenReturn(java.util.Optional.empty());

        String result = bookingService.cancelBooking(nonExistingBookingId);

        verify(bookingRepository, never()).delete(any());

        assertEquals("Reservation not available.", result);
    }
}
