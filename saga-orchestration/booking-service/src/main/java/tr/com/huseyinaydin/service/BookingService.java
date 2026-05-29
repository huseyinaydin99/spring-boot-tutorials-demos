package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Booking;
import tr.com.huseyinaydin.events.BookingCreatedEvent;
import tr.com.huseyinaydin.repository.BookingRepository;
import tr.com.huseyinaydin.utils.mapper.BookingRequestToEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void processBooking(BookingCreatedEvent request) {
        log.info("BookingService:: ilgili kullanıcı için rezervasyon işleniyor {} gösterim id: {}", request.userId(), request.showId());
        Booking booking = null;
        Booking existingBooking = bookingRepository
                .findByBookingCode(request.bookingId());

        if (existingBooking == null) {
            // CREATE
            log.info("BookingService:: ilgili id için yeni rezervasyon oluşturuluyor {}", request.bookingId());
            booking = BookingRequestToEntityMapper.mapEvents(request);
        } else {
            //UPDATE
            booking = updateExistingBooking(existingBooking, request);
            log.info("BookingService::  ilgili id için yeni rezervasyon güncelleniyor {}", request.bookingId());
        }
        // Yeni veya güncellenmiş rezervasyonu kaydet
        var saved = bookingRepository.save(booking);

        log.info("BookingService:: Rezervasyon kaydedildi: rezervasyon id {} | durumu/statusu {}",
                saved.getBookingCode(), saved.getStatus());
    }

    private Booking updateExistingBooking(Booking existing, BookingCreatedEvent event) {
        // CONFIRMED/ONAYLI ve FAILED/HATALI akışları için yalnızca anlamlı olan alanları güncelle
        existing.setStatus(event.status());
        return existing;
    }
}