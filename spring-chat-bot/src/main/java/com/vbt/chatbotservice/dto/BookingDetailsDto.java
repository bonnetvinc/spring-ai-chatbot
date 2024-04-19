package com.vbt.chatbotservice.dto;


import com.vbt.chatbotservice.data.BookingStatus;
import com.vbt.chatbotservice.data.BookingZone;

import java.time.LocalDate;

public record BookingDetailsDto(String bookingNumber,
                                String firstName,
                                String lastName,
                                LocalDate date,
                                BookingStatus bookingStatus,

                                BookingZone bookingZone) {
}
