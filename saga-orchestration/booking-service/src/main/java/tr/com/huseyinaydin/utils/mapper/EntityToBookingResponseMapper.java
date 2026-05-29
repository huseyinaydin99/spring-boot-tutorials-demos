package tr.com.huseyinaydin.utils.mapper;

import tr.com.huseyinaydin.entity.Booking;
import tr.com.huseyinaydin.response.BookingResponse;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public class EntityToBookingResponseMapper {

    public static BookingResponse map(Booking booking) {
        return new BookingResponse(booking.getBookingCode(),
                booking.getStatus());
    }
}