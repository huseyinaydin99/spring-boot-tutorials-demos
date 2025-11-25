package tr.org.huseyinaydin.messaging;

import tr.org.huseyinaydin.events.SeatReservedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Component
@Slf4j
public class SeatReserveProducer {

    private KafkaTemplate<String, SeatReservedEvent> template;

    public SeatReserveProducer(KafkaTemplate<String, SeatReservedEvent> template) {
        this.template = template;
    }

    public void publishSeatReserveEvents(SeatReservedEvent reservedEvent) {
        try {
            log.info("SeatReserveProducer:: Koltuk rezerve olayını/event'ini yayınlama bookingId {}", reservedEvent.bookingId());
            template.send(SEAT_RESERVED_TOPIC,reservedEvent.bookingId(), reservedEvent);
        } catch (Exception e) {
            log.error("SeatReserveProducer:: seatReserved olayı/event'i yayınlanırken hata oluştu bookingId {}: {}", reservedEvent.bookingId(), e.getMessage());
        }
    }
}