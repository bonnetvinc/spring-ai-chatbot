package com.vbt.chatbotservice.service;

import com.vbt.chatbotservice.data.*;
import com.vbt.chatbotservice.dto.BookingDetailsDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ReservationService {

    private final BookingData db;

    public ReservationService() {
        db = new BookingData();
        initDemoData();
    }

    private void initDemoData() {
        List<String> firstNames = List.of("John", "Jane", "Michael", "Sarah", "Robert");
        List<String> lastNames = List.of("Doe", "Smith", "Johnson", "Williams", "Taylor");
        List<String> tableZones = List.of("BAR", "ROOFTOP", "OTHER");
        Random random = new Random();

        var customers = new ArrayList<Customer>();
        var bookings = new ArrayList<Booking>();

        for (int i = 0; i < 5; i++) {
            String firstName = firstNames.get(i);
            String lastName = lastNames.get(i);
            BookingZone bookingZone = BookingZone.values()[random.nextInt(BookingZone.values().length)];
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);

            LocalDate date = LocalDate.now().plusDays(2*i);

            Booking booking = new Booking("10" + (i + 1), date, customer, BookingStatus.CONFIRMED, bookingZone);
            customer.getBookings().add(booking);

            customers.add(customer);
            bookings.add(booking);
        }

        // Reset the database on each start
        db.setCustomers(customers);
        db.setBookings(bookings);

        System.out.println("Demo data initialized");
    }


    public BookingDetailsDto createBooking(String firstName, String lastName, String date, BookingZone zone) {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Booking booking = new Booking("222", LocalDate.parse(date), customer, BookingStatus.CONFIRMED, zone);

        db.getBookings().add(booking);
        return toBookingDetails(booking);
    }

    public List<BookingDetailsDto> getBookings() {
        return db.getBookings().stream().map(this::toBookingDetails).toList();
    }

    private Booking findBooking(String bookingNumber, String firstName, String lastName) {
        return db.getBookings().stream()
                .filter(b -> b.getBookingNumber().equalsIgnoreCase(bookingNumber))
                .filter(b -> b.getCustomer().getFirstName().equalsIgnoreCase(firstName))
                .filter(b -> b.getCustomer().getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }


    private List<Booking> listBooking( String firstName, String lastName) {
        return db.getBookings().stream()
                .filter(b -> b.getCustomer().getFirstName().equalsIgnoreCase(firstName))
                .filter(b -> b.getCustomer().getFirstName().equalsIgnoreCase(firstName))
                .filter(b -> b.getCustomer().getLastName().equalsIgnoreCase(lastName)).toList();
    }

    public BookingDetailsDto getBookingDetails(String bookingNumber, String firstName, String lastName) {
        var booking = findBooking(bookingNumber, firstName, lastName);
        return toBookingDetails(booking);
    }

    public List<BookingDetailsDto> listBookingDetails(String firstName, String lastName) {
        var booking = listBooking(firstName, lastName);
        return toBookingDetails(booking);
    }

    public void changeBooking(String bookingNumber, String firstName, String lastName, String newDate) {
        var booking = findBooking(bookingNumber, firstName, lastName);
        if(booking.getDate().isBefore(LocalDate.now().plusDays(1))){
            throw new IllegalArgumentException("Booking cannot be changed within 24 hours of the start date.");
        }
        booking.setDate(LocalDate.parse(newDate));
    }

    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        var booking = findBooking(bookingNumber, firstName, lastName);
        if (booking.getDate().isBefore(LocalDate.now().plusDays(2))) {
            throw new IllegalArgumentException("Booking cannot be cancelled within 48 hours of the start date.");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }


    private List<BookingDetailsDto> toBookingDetails(List<Booking> bookings){
        return bookings.stream().map(this::toBookingDetails).toList();
    }

    private BookingDetailsDto toBookingDetails(Booking booking){
        return new BookingDetailsDto(
                booking.getBookingNumber(),
                booking.getCustomer().getFirstName(),
                booking.getCustomer().getLastName(),
                booking.getDate(),
                booking.getBookingStatus(),
                booking.getBookingZone()
        );
    }
}
