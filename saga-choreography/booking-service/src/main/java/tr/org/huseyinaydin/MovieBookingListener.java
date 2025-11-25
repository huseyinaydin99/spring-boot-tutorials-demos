package tr.org.huseyinaydin;

import tr.org.huseyinaydin.events.SeatReservedEvent;
import tr.org.huseyinaydin.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.MOVIE_BOOKING_GROUP;
import static tr.org.huseyinaydin.common.KafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Component
@Slf4j
public class MovieBookingListener {

    private BookingService service;

    public MovieBookingListener(BookingService service) {
        this.service = service;
    }

    @KafkaListener(topics = SEAT_RESERVED_TOPIC, groupId = MOVIE_BOOKING_GROUP)
    public void consumeSeatReserveEvents(SeatReservedEvent event) {
        log.info("MovieBookingListener:: seatReserved etkinliği tüketiliyor");

        if (event.reserved()) {
            log.info("Rezervasyon işlemi tamamlandı bookingId: {}", event.bookingId());
        } else {
            //rollback
            log.info("Koltuk rezervasyonu başarısız oldu bookingId: {}", event.bookingId());
            service.handleBookingOnSeatReservationFailure(event.bookingId());
        }
    }
}