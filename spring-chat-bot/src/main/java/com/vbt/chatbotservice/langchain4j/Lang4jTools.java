package com.vbt.chatbotservice.langchain4j;

import com.vbt.chatbotservice.data.BookingZone;
import com.vbt.chatbotservice.dto.BookingDetailsDto;
import com.vbt.chatbotservice.service.ReservationService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Lang4jTools {

    private final ReservationService service;

    public Lang4jTools(ReservationService service) {
        this.service = service;
    }

    @Tool
    public BookingDetailsDto getBookingDetails(String bookingNumber, String firstName, String lastName) {
        return service.getBookingDetails(bookingNumber, firstName, lastName);
    }

    @Tool
    public List<BookingDetailsDto> listBookingDetails(String firstName, String lastName) {
        return service.listBookingDetails(firstName, lastName);
    }
    @Tool
    public void createBooking(String firstName, String lastName, String date, BookingZone zone ) {
        service.createBooking( firstName, lastName, date, zone);
    }

    @Tool
    public void changeBooking(String bookingNumber, String firstName, String lastName, String date) {
        service.changeBooking(bookingNumber, firstName, lastName, date);
    }

    @Tool
    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        service.cancelBooking(bookingNumber, firstName, lastName);
    }

}
