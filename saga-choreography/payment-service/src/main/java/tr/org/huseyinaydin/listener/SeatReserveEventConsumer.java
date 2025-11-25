package tr.org.huseyinaydin.listener;

import tr.org.huseyinaydin.events.SeatReservedEvent;
import tr.org.huseyinaydin.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.PAYMENT_EVENT_GROUP;
import static tr.org.huseyinaydin.common.KafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatReserveEventConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = SEAT_RESERVED_TOPIC, groupId = PAYMENT_EVENT_GROUP)
    public void consume(SeatReservedEvent event) {
        try {
            log.info("Koltuk rezerve olayı/event'i tüketildi/alındı bookingId: {} ve olay/event: {}", event.bookingId(), event);
            if(event.reserved()) {
                paymentService.processPayment(event);
            }else{
                log.info("Koltuk rezervasyonu başarısız olduğundan ödeme işlemi atlanıyor bookingId: {}", event.bookingId());
            }
        } catch (Exception e) {
            log.error("Koltuk rezerve olayı/event'i işlenirken hata oluştu bookingId {}: {}", event.bookingId(), e.getMessage());
        }
    }
}