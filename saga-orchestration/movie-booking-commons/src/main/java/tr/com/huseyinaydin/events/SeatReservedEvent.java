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

public record SeatReservedEvent(String bookingId, String showId, List<String> seatIds, boolean reserved, long amount) {}