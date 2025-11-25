package tr.org.huseyinaydin.listener;

import tr.org.huseyinaydin.events.BookingCreatedEvent;
import tr.org.huseyinaydin.service.SeatInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;
import static tr.org.huseyinaydin.common.KafkaConfigProperties.SEAT_EVENT_GROUP;

@Component
@Slf4j
public class SeatInventoryListener {

    private final SeatInventoryService seatInventoryService;

    public SeatInventoryListener(SeatInventoryService seatInventoryService) {
        this.seatInventoryService = seatInventoryService;
    }

    @KafkaListener(topics = MOVIE_BOOKING_EVENTS_TOPIC, groupId = SEAT_EVENT_GROUP)
    public void consumeBookingEvents(BookingCreatedEvent event) {
        // Rezervasyon etkinliğine göre koltuk envanterini güncelleme mantığı
        log.info("SeatInventoryListener:: Rezervasyon olayı oluşturma tüketimi bookingId {}", event.bookingId());
        seatInventoryService.handleBooking(event);
    }
}