package tr.org.huseyinaydin.service;

import tr.org.huseyinaydin.entity.SeatInventory;
import tr.org.huseyinaydin.events.BookingCreatedEvent;
import tr.org.huseyinaydin.events.SeatReservedEvent;
import tr.org.huseyinaydin.messaging.SeatReserveProducer;
import tr.org.huseyinaydin.repository.SeatInventoryRepository;
import tr.org.huseyinaydin.utils.enums.SeatStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeatInventoryService {

    private final SeatInventoryRepository seatInventoryRepository;
    private final SeatReserveProducer seatReserveProducer;

    public SeatInventoryService(SeatInventoryRepository seatInventoryRepository,
                                SeatReserveProducer seatReserveProducer) {
        this.seatReserveProducer = seatReserveProducer;
        this.seatInventoryRepository = seatInventoryRepository;
    }

    public void handleBooking(BookingCreatedEvent event) {
        log.info("SeatInventoryService:: Rezervasyon oluşturma işlemi bookingId {}", event.bookingId());

        // Belirtilen gösteri ve koltuk numaraları için koltuk envanterlerini getirir.
        List<SeatInventory> seats = seatInventoryRepository
                .findByShowIdAndSeatNumberIn(event.showId(), event.seatIds());

        // Tüm koltukların müsait olup olmadığını kontrol eder.
        boolean allAvailable = seats.stream()
                .allMatch(s -> s.getStatus() == SeatStatus.AVAILABLE);

        if (allAvailable) {
            // Koltuk durumunu LOCKED (kilitli) olarak günceller ve geçerli rezervasyon kimliğini (booking ID) ayarlar.
            seats.forEach(s -> {
                s.setStatus(SeatStatus.LOCKED);
                s.setCurrentBookingId(event.bookingId());
            });
            seatInventoryRepository.saveAll(seats);
            // Koltuk rezerve edildi olayını yayınlar.
            seatReserveProducer.publishSeatReserveEvents(new SeatReservedEvent(event.bookingId(), true, event.amount()));
            log.info("SeatInventoryService:: Koltuk kilitlme başarılı bookingId {}", event.bookingId());
        }else{
            log.warn("SeatInventoryService:: Koltuk kilitlme başarısız bookingId {}. Bazı koltuklar müsait değil.", event.bookingId());
            // Başarısız koltuk rezervasyonu olayını yayınlar.
            seatReserveProducer.publishSeatReserveEvents(new SeatReservedEvent(event.bookingId(), false, event.amount()));
        }
    }

    public void releaseSeatsOnPaymentFailure(String bookingId) {
        log.info("SeatInventoryService:: Koltukları boşalt bookingId {}", bookingId);

        List<SeatInventory> bookingSeats = seatInventoryRepository.findByCurrentBookingId(bookingId);

        bookingSeats.forEach(s -> {
            s.setStatus(SeatStatus.AVAILABLE);
            s.setCurrentBookingId(null);
        });

        seatInventoryRepository.saveAll(bookingSeats);
        log.info("SeatInventoryService:: Koltuk boşaltılması başarılı bookingId {}", bookingId);

        //Başarısızlık olayını aşağı akıştaki (booking-service) servisine gönderir.

        seatReserveProducer.publishSeatReserveEvents(new SeatReservedEvent(bookingId, false, 0));
    }
}