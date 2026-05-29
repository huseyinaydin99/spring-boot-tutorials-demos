package tr.com.huseyinaydin.events;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public record BookingCreatedEvent(String bookingId, String userId, String showId, List<String> seatIds, long amount, String status) {}