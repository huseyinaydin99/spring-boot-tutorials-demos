package tr.com.huseyinaydin.listener;

import tr.com.huseyinaydin.events.BookingPaymentEvent;
import tr.com.huseyinaydin.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static tr.com.huseyinaydin.common.KafkaConfigProperties.PAYMENT_EVENTS_CMD_TOPIC;
import static tr.com.huseyinaydin.common.KafkaConfigProperties.PAYMENT_EVENT_GROUP;

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
public class PaymentEventListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = PAYMENT_EVENTS_CMD_TOPIC, groupId = PAYMENT_EVENT_GROUP)
    public void onPaymentEvents(BookingPaymentEvent event){
        try {
            log.info("PaymentEventListener:: Ödeme olayları işleniyor");
            paymentService.processPayment(event);
        }catch (Exception e){
            log.error("bookingId için ödeme olayı işlenirken hata oluştu {}: {}", event.bookingId(), e.getMessage());
        }
    }
}