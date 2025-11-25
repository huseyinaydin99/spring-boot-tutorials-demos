package tr.org.huseyinaydin.messaging;

import tr.org.huseyinaydin.common.KafkaConfigProperties;
import tr.org.huseyinaydin.events.BookingCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;

@Component
@Slf4j
public class BookingEventProducer {

    private KafkaTemplate<String, Object> template;

    public BookingEventProducer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void publishBookingEvents(BookingCreatedEvent createdEvent) {
        try {
            log.info("Verilen rezervasyon olayını yayınlama ID : {}", createdEvent.bookingId());
            template.send(MOVIE_BOOKING_EVENTS_TOPIC, createdEvent.bookingId(), createdEvent);
        } catch (Exception e) {
            log.error("Rezervasyon etkinliği yayınlanırken hata oluştu ID : {}",
                    createdEvent.bookingId(), e);
        }
    }
}