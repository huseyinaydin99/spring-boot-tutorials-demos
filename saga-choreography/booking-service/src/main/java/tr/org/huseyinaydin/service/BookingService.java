package tr.org.huseyinaydin.service;

import tr.org.huseyinaydin.entity.Booking;
import tr.org.huseyinaydin.events.BookingCreatedEvent;
import tr.org.huseyinaydin.messaging.BookingEventProducer;
import tr.org.huseyinaydin.repository.BookingRepository;
import tr.org.huseyinaydin.request.BookingRequest;
import tr.org.huseyinaydin.response.BookingResponse;
import tr.org.huseyinaydin.utils.mapper.EntityToBookingResponseMapper;
import tr.org.huseyinaydin.utils.mapper.BookingRequestToEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    private final BookingEventProducer bookingEventProducer;

    public BookingService(BookingRepository bookingRepository, BookingEventProducer bookingEventProducer) {
        this.bookingEventProducer = bookingEventProducer;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Belirli bir gösteri ve kullanıcı için koltukları rezerve eder.
     * İsteği doğrular, bir entity’ye dönüştürür, kalıcı hale getirir ve bir response DTO döndürür.
     *
     * @param request koltuk rezervasyon isteği
     * @return Rezervasyon detaylarını içeren SeatReserveResponse
     * @throws IllegalArgumentException istek geçersiz olduğunda fırlatılır
     */

    public BookingResponse bookSeats(BookingRequest request) {
        log.info("{} kullanıcısı {} gösterisi için koltuk rezervasyonu yapılıyor", request.userId(), request.showId());

        // İsteği eşler -> entity
        var reservationEntity = BookingRequestToEntityMapper.map(request);

        // Kalıcı hale getirir ve kaydedileni nesneye eşler.
        var savedReservation = bookingRepository.save(reservationEntity);

        // Oluşturulan etkinliğin rezervasyonunu yayınla
        var bookingCreatedEvent = buildBookingCreateEvents(savedReservation);
        bookingEventProducer.publishBookingEvents(bookingCreatedEvent);

        var response = EntityToBookingResponseMapper.map(savedReservation);

        log.info("Rezervasyon kimliği ile koltuklar onaylandı {}", response.reservationId());
        return response;
    }

    private BookingCreatedEvent buildBookingCreateEvents(Booking savedReservation) {
        return new BookingCreatedEvent(savedReservation.getBookingCode(), savedReservation.getUserId(), savedReservation.getShowId(), savedReservation.getSeatIds(), savedReservation.getAmount());
    }

    public void handleBookingOnSeatReservationFailure(String bookingId) {
        log.info("BookingService:: bookingId için rezervasyon hatasının işlenmesi\n {}", bookingId);
        var bookingDetails = bookingRepository.findByBookingCode(bookingId);
        if (bookingDetails != null) {
            bookingDetails.setStatus("FAILED");
            bookingRepository.save(bookingDetails);
            log.info("BookingService:: BookingId için rezervasyon BAŞARISIZ/HATALI olarak işaretlendi {}", bookingId);
        } else {
            log.warn("BookingService:: bookingId ile rezervasyon bulunamadı\n {}", bookingId);
        }
    }
}