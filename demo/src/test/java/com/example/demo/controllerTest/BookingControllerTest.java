package com.example.demo.controllerTest;
import com.example.demo.modell.Booking;
import com.example.demo.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookingService bookingService;
    @Test
    public void BookTestTransport() throws Exception {
        Booking booking = new Booking();
        booking.setTransportationType("Car");
        Mockito.when(bookingService.bookTransport(Mockito.any(Booking.class))).thenReturn("Your transportation has been booked successfully!");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/book")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Your transportation has been booked successfully!"));
    }
    @Test
    public void BookingTestCancel() throws Exception {
        Long bookingId = 1L;
        Mockito.when(bookingService.cancelBooking(Mockito.anyLong())).thenReturn("Your reservation has been successfully cancelled!!!");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bookings/cancel/{bookingId}", bookingId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Your reservation has been successfully cancelled!!!"));
    }
}