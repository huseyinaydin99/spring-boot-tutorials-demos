package tr.org.huseyinaydin.listener;

import tr.org.huseyinaydin.events.BookingPaymentEvent;
import tr.org.huseyinaydin.service.SeatInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static tr.org.huseyinaydin.common.KafkaConfigProperties.PAYMENT_EVENTS_TOPIC;
import static tr.org.huseyinaydin.common.KafkaConfigProperties.SEAT_EVENT_GROUP;

@Component
@Slf4j
public class PaymentStatusListener {

    private SeatInventoryService service;

    public PaymentStatusListener(SeatInventoryService service) {
        this.service = service;
    }

    @KafkaListener(topics = PAYMENT_EVENTS_TOPIC, groupId = SEAT_EVENT_GROUP)
    public void consumePaymentStatusEvents(BookingPaymentEvent event) {
        log.info("PaymentStatusListener:: Rezervasyon ödeme durumu olayı tüketiliyor\n {}", event.bookingId());

        if (event.paymentCompleted()) {
            log.info("Ödeme durumu başarılı oldu bookingId: {}", event.bookingId());
        } else {
            log.info("Ödeme başarısız oldu bookingId: {}, koltuklar boşaltıldı.", event.bookingId());
            service.releaseSeatsOnPaymentFailure(event.bookingId());
        }
    }
}