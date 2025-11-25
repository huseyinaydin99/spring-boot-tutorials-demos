package tr.org.huseyinaydin.utils.mapper;

import tr.org.huseyinaydin.entity.Booking;
import tr.org.huseyinaydin.request.BookingRequest;

import java.util.UUID;

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
}