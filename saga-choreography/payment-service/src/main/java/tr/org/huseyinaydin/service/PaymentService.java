package tr.org.huseyinaydin.service;

import tr.org.huseyinaydin.PaymentEventsProducer;
import tr.org.huseyinaydin.events.BookingPaymentEvent;
import tr.org.huseyinaydin.events.SeatReservedEvent;
import tr.org.huseyinaydin.exception.PaymentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private PaymentEventsProducer eventsProducer;

    public void processPayment(SeatReservedEvent event) {
        try {
            log.info("Ödeme işleniyor bookingId: {}", event.bookingId());

            // Ödeme başarısızlığı senaryosunu simüle ediyorum kardeş kafaya göre tabi
            if (event.amount() > 2000) {
                log.info("Ödeme tutarı limiti aşıyor bookingId: {}", event.bookingId());
                // arıza olayları/event'leri
                eventsProducer.publishPaymentFailureEvent(event);
                //throw new RuntimeException("Ödeme tutarı limiti aşılıyor o yüzden hata");
            } else {
                // olay başarılı olma durumu
                kafkaTemplate.send("payment-events",
                        new BookingPaymentEvent(event.bookingId(), true, event.amount()));
                eventsProducer.publishPaymentSuccessEvent(event);
                log.info("✅ Ödeme işlemi başarılı bookingId: {}", event.bookingId());
            }
        } catch (Exception e) {
            log.error("❌ Ödeme işlemi başarısız bookingId: {}. Sebebi: {}", event.bookingId(), e.getMessage());
            throw new PaymentServiceException("Ödeme işlemi başarısız bookingId: " + event.bookingId());
        }
    }
}