package tr.com.huseyinaydin.listener;

import tr.com.huseyinaydin.events.BookingCreatedEvent;
import tr.com.huseyinaydin.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static tr.com.huseyinaydin.common.KafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;
import static tr.com.huseyinaydin.common.KafkaConfigProperties.MOVIE_BOOKING_GROUP;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class MovieBookingConsumer {
    private final BookingService service;

    @KafkaListener(topics = MOVIE_BOOKING_EVENTS_TOPIC, groupId = MOVIE_BOOKING_GROUP)
    public void processBookingRequest(BookingCreatedEvent event){
        try {
            log.info("BookingListener:: ilgili id için booking olayı tüketiliyor : {}", event.bookingId());
            service.processBooking(event);
        } catch (Exception e) {
            log.error("BookingListener:: ilgili id için booking olayı işlenirken hata oluştu : {}", event.bookingId(), e);
        }
    }
}