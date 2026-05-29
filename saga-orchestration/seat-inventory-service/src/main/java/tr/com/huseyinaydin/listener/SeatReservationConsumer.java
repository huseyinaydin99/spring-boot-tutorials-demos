package tr.com.huseyinaydin.listener;

import tr.com.huseyinaydin.events.SeatReservedEvent;
import tr.com.huseyinaydin.service.SeatInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static tr.com.huseyinaydin.common.KafkaConfigProperties.SEAT_EVENT_GROUP;
import static tr.com.huseyinaydin.common.KafkaConfigProperties.SEAT_RESERVED_CMD_TOPIC;

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
public class SeatReservationConsumer {

    private final SeatInventoryService service;

    @KafkaListener(topics = SEAT_RESERVED_CMD_TOPIC, groupId = SEAT_EVENT_GROUP)
    public void onSeatReserveEvent(SeatReservedEvent event){
        log.info("SeatReserveEventListener:: Koltuk rezervasyonu etkinliğinin tüketimi");

        if(event.reserved()){
            //koltuğu ayırt
            service.reserveSeats(event);
            log.info("SeatReserveEventListener:: Koltuklar başarıyla rezerve edildi bookingId: {}", event.bookingId());
        }else{
            //rollback
            service.rollbackSeatReservationOnFailure(event.bookingId());
            log.warn("SeatReserveEventListener:: Koltuk rezervasyonu başarısız oldu bookingId: {}. Kilitli koltukları geri çek", event.bookingId());
        }
    }
}