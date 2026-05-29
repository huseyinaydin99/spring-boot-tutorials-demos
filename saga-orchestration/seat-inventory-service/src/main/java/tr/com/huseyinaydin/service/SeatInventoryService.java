package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.SeatInventory;
import tr.com.huseyinaydin.events.SeatReservedEvent;
import tr.com.huseyinaydin.messaging.SeatReserveProducer;
import tr.com.huseyinaydin.repository.SeatInventoryRepository;
import tr.com.huseyinaydin.utils.enums.SeatStatus;
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

    //Koltuk envanterini yönetmek için servis yöntemleri ekle.

    public void reserveSeats(SeatReservedEvent event) {
        log.info("SeatInventoryService:: bookingCreated çalıştırıldı bookingId {}", event.bookingId());

        // Verilen gösteri ve koltuk numaraları için koltuk envanterlerini getir.
        List<SeatInventory> seats = seatInventoryRepository
                .findByShowIdAndSeatNumberIn(event.showId(), event.seatIds());

        // Tüm koltukların müsait olup olmadığını kontrol et.
        boolean allAvailable = seats.stream()
                .allMatch(s -> s.getStatus() == SeatStatus.AVAILABLE);

        if (allAvailable) {
            // Koltuk durumunu KİLİTLİ olarak güncelleyin ve geçerli rezervasyon kimliğini ayarla.
            seats.forEach(s -> {
                s.setStatus(SeatStatus.LOCKED);
                s.setCurrentBookingId(event.bookingId());
            });
            seatInventoryRepository.saveAll(seats);
            // Rezervasyonlu koltuk etkinliğini yayınla
            seatReserveProducer
                    .publishSeatReserveEvents(new SeatReservedEvent(event.bookingId(), event.showId(), event.seatIds(), true, event.amount()));
            log.info("SeatInventoryService:: Koltuk kilitleme başarılı bookingId: {}", event.bookingId());
        } else {
            log.warn("SeatInventoryService:: Koltuk kilitleme başarılı bookingId {}. Bazı koltuklar müsait değil.", event.bookingId());
            // Publish seat reserved event with failure
            seatReserveProducer
                    .publishSeatReserveEvents(new SeatReservedEvent(event.bookingId(), event.showId(), event.seatIds(), false, event.amount()));
        }
    }

    public void rollbackSeatReservationOnFailure(String bookingId) {
        log.info("SeatInventoryService:: Koltuklar serbest bırakılıyor bookingId: {}", bookingId);

        List<SeatInventory> bookingSeats = seatInventoryRepository.findByCurrentBookingId(bookingId);

        bookingSeats.forEach(s -> {
            s.setStatus(SeatStatus.AVAILABLE);
            s.setCurrentBookingId(null);
        });

        seatInventoryRepository.saveAll(bookingSeats);
        log.info("SeatInventoryService:: Koltuklar başarıyla serbest bırakıldı bookingId: {}", bookingId);

        //Başarısız olayı alt kademeye gönder (booking-service)

        seatReserveProducer
                .publishSeatReserveEvents(new SeatReservedEvent(bookingId, null, null, false, 0));
    }
}