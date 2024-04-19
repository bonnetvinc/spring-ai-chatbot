package com.vbt.chatbotservice.data;

import java.time.LocalDate;

public class Booking {

    private String bookingNumber;
    private LocalDate date;
    private LocalDate bookingTo;
    private Customer customer;
    private BookingStatus bookingStatus;
    private BookingZone bookingZone;

    public Booking(String bookingNumber, LocalDate date, Customer customer, BookingStatus bookingStatus, BookingZone bookingZone) {
        this.bookingNumber = bookingNumber;
        this.date = date;
        this.customer = customer;
        this.bookingStatus = bookingStatus;
        this.bookingZone = bookingZone;
    }


    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getBookingTo() {
        return bookingTo;
    }

    public void setBookingTo(LocalDate bookingTo) {
        this.bookingTo = bookingTo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingZone getBookingZone() {
        return bookingZone;
    }

    public void setBookingZone(BookingZone bookingZone) {
        this.bookingZone = bookingZone;
    }


}