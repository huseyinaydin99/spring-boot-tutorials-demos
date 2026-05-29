package tr.com.huseyinaydin.request;

import java.time.Instant;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public record BookingRequest(String reservationId, String showId,
                             List<String> seatIds, String userId,
                             Instant timestamp,long amount) {
}