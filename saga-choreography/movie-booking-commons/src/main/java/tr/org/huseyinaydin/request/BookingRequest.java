package tr.org.huseyinaydin.request;

import java.time.Instant;
import java.util.List;

public record BookingRequest(String reservationId, String showId,
                             List<String> seatIds, String userId,
                             Instant timestamp,long amount) {
}