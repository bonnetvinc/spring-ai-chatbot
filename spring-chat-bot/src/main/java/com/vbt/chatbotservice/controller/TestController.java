package com.vbt.chatbotservice.controller;

import com.vbt.chatbotservice.dto.BookingDetailsDto;
import com.vbt.chatbotservice.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {

    private final ReservationService carRentalService;

    public TestController(ReservationService carRentalService) {
        this.carRentalService = carRentalService;
    }

    @GetMapping("/viewBookings")
    public List<BookingDetailsDto> test() {
            return carRentalService.getBookings();
    }
}
