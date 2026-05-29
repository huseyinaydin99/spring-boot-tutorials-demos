package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.common.KafkaConfigProperties;
import tr.com.huseyinaydin.events.BookingCreatedEvent;
import tr.com.huseyinaydin.events.BookingPaymentEvent;
import tr.com.huseyinaydin.events.SeatReservedEvent;
import tr.com.huseyinaydin.request.BookingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static tr.com.huseyinaydin.common.KafkaConfigProperties.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieBookingOrchestrator {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public String createBooking(BookingRequest request) {
        // Rezervasyon oluşturuldu olayı gönder
        String bookingId = generateBookingId();
        BookingCreatedEvent bookingCreatedEvent = buildBookingCreateEvents(request, bookingId);
        kafkaTemplate.send(MOVIE_BOOKING_EVENTS_TOPIC, bookingId, bookingCreatedEvent);
        log.info("MovieBookingOrchestrator:: bookingId için bookingCreated olayı yayımlandı: {}", bookingId);

        // Koltuk rezervasyon isteğini hazırla ve gönder
        SeatReservedEvent seatReserveRequest = new SeatReservedEvent(bookingId, request.showId(), request.seatIds(), true, request.amount());
        kafkaTemplate.send(KafkaConfigProperties.SEAT_RESERVED_CMD_TOPIC, bookingId, seatReserveRequest);
        log.info("MovieBookingOrchestrator:: bookingId için koltuk rezervasyon isteği yayımlandı: {}", bookingId);

        return bookingId;
    }

    @KafkaListener(topics = SEAT_RESERVED_TOPIC, groupId = ORCHESTRATOR_CONSUMER_GROUP)
    public void onSeatReserve(SeatReservedEvent event) {
        log.info("MovieBookingOrchestrator:: bookingId için seatReserved olayı tüketiliyor: {}", event.bookingId());

        if (event.reserved()) {
            sendPaymentRequest(event);
            log.info("Orchestrator BookingPaymentEvent (isteğini) {} yayımladı", "Payment-Service");
        } else {
            sendRollbackToBookingService(event);
            log.info("Orchestrator BookingCreatedEvent (HATA) {} yayımladı", "Booking-Service");
        }
    }

    @KafkaListener(topics = PAYMENT_EVENTS_TOPIC, groupId = ORCHESTRATOR_CONSUMER_GROUP)
    public void onPaymentStatus(BookingPaymentEvent event) {
        log.info("MovieBookingOrchestrator:: Consuming payment event for bookingId: {}", event.bookingId());
        if (event.paymentCompleted()) {
            //Rezervasyon durumunu onaylandı olarak güncelle
            confirmBookingStatus(event);
            log.info("Orchestrator BookingCreatedEvent'i yayımladı (ONAYLANDI)");
        } else {
            //Koltuk envanter servisine geri alma (rollback) komutu gönder çünkü hata oldu
            SeatReservedEvent seatFailureEvent = new SeatReservedEvent(event.bookingId(), event.showId(), event.seatIds(), false, event.amount());
            kafkaTemplate.send(SEAT_RESERVED_CMD_TOPIC, event.bookingId(), seatFailureEvent);
            log.info("Orchestrator SeatReservedEvent (sertbest bırakma/hata) {} olayı yayımlandı", "Seat-Service");
        }
    }

    private void confirmBookingStatus(BookingPaymentEvent event) {
        BookingCreatedEvent bookingSuccessEvent = new BookingCreatedEvent(
                event.bookingId(),
                null,
                event.showId(),
                event.seatIds(),
                event.amount(),
                "CONFIRMED"
        );
        kafkaTemplate.send(MOVIE_BOOKING_EVENTS_TOPIC, event.bookingId(), bookingSuccessEvent);
    }

    private void sendRollbackToBookingService(SeatReservedEvent event) {
        BookingCreatedEvent bookingFailureEvent = new BookingCreatedEvent(
                event.bookingId(),
                null,
                event.showId(),
                event.seatIds(),
                event.amount(),
                "FAILED"
        );
        kafkaTemplate.send(MOVIE_BOOKING_EVENTS_TOPIC, event.bookingId(), bookingFailureEvent);
    }

    private void sendPaymentRequest(SeatReservedEvent event) {
        BookingPaymentEvent paymentEvent = new BookingPaymentEvent(event.bookingId(), event.showId(), event.seatIds(), false, event.amount());
        kafkaTemplate.send(PAYMENT_EVENTS_CMD_TOPIC, event.bookingId(), paymentEvent);
    }

    private static BookingCreatedEvent buildBookingCreateEvents(BookingRequest request, String bookingId) {
        return new BookingCreatedEvent(
                bookingId,
                request.userId(),
                request.showId(),
                request.seatIds(),
                request.amount(),
                "PENDING"
        );
    }

    private String generateBookingId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}