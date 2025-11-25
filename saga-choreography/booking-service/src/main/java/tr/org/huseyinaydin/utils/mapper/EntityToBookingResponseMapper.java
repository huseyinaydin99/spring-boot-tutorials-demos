package tr.org.huseyinaydin.utils.mapper;

import tr.org.huseyinaydin.entity.Booking;
import tr.org.huseyinaydin.response.BookingResponse;

public class EntityToBookingResponseMapper {

    public static BookingResponse map(Booking booking) {
        return new BookingResponse(booking.getBookingCode(),
                booking.getStatus());
    }
}