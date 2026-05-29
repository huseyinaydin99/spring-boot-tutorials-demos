package tr.com.huseyinaydin.utils.mapper;

import tr.com.huseyinaydin.entity.Booking;
import tr.com.huseyinaydin.events.BookingCreatedEvent;
import tr.com.huseyinaydin.request.BookingRequest;

import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public class BookingRequestToEntityMapper {

    public static Booking map(BookingRequest request) {
        var reservationCode = UUID.randomUUID().toString().split("-")[0];
        Booking booking = new Booking();
        booking.setId(System.currentTimeMillis());
        booking.setShowId(request.showId());
        booking.setBookingCode(reservationCode);
        booking.setSeatIds(request.seatIds());
        booking.setUserId(request.userId());
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(java.time.Instant.now());
        booking.setAmount(request.amount());
        return booking;
    }

    public static Booking mapEvents(BookingCreatedEvent request) {
        Booking booking = new Booking();
        booking.setId(System.currentTimeMillis());
        booking.setShowId(request.showId());
        booking.setBookingCode(request.bookingId());
        booking.setSeatIds(request.seatIds());
        booking.setUserId(request.userId());
        booking.setStatus(request.status());
        booking.setCreatedAt(java.time.Instant.now());
        booking.setAmount(request.amount());
        return booking;
    }
}