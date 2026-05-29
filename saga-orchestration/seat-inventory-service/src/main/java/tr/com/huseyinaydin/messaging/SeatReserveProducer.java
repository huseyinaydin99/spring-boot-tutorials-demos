package tr.com.huseyinaydin.messaging;

import tr.com.huseyinaydin.events.SeatReservedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static tr.com.huseyinaydin.common.KafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Component
@Slf4j
public class SeatReserveProducer {

    private KafkaTemplate<String, SeatReservedEvent> template;

    public SeatReserveProducer(KafkaTemplate<String, SeatReservedEvent> template) {
        this.template = template;
    }

    public void publishSeatReserveEvents(SeatReservedEvent reservedEvent) {
        try {
            log.info("SeatReserveProducer:: Üretim Rezervasyonlu etkinlik (rezervasyon kimliği için) {}", reservedEvent.bookingId());
            template.send(SEAT_RESERVED_TOPIC,reservedEvent.bookingId(), reservedEvent);
        } catch (Exception e) {
            log.error("SeatReserveProducer:: seatReserved etkinliğini yayınlarken hata oluştu bookingId: {}: {}", reservedEvent.bookingId(), e.getMessage());
        }
    }
}
