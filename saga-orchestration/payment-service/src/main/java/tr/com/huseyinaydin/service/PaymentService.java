package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.events.BookingPaymentEvent;
import tr.com.huseyinaydin.producer.PaymentEventsProducer;
import tr.com.huseyinaydin.exception.PaymentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private PaymentEventsProducer eventsProducer;

    public void processPayment(BookingPaymentEvent event) {
        try {
            log.info("bookingId için ödeme olayı işleniyor: {}", event.bookingId());
            // Ödeme başarısızlık senaryosu simüle ediliyor
            if (event.amount() > 2000) {
                log.info("bookingId için ödeme tutarı limiti aşıyor: {}", event.bookingId());
                // Başarısızlık olayları
                eventsProducer.publishPaymentFailureEvent(event);
                //throw new RuntimeException("Ödeme tutarının limiti aşıyor");
            } else {
                // başarılı olay
                eventsProducer.publishPaymentSuccessEvent(event);
                log.info("✅ bookingId için ödeme başarıyla tamamlandı: {}", event.bookingId());
            }
        } catch (Exception e) {
            log.error("❌ bookingId için ödeme başarısız oldu: {}. Sebep: {}", event.bookingId(), e.getMessage());
            throw new PaymentServiceException("bookingId için ödeme işlemi başarısız oldu: " + event.bookingId());
        }
    }
}