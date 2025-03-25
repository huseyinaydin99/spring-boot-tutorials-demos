package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Seat;
import tr.com.huseyinaydin.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class MovieTicketBookingService {

    @Autowired
    private SeatRepository seatRepository;

    //optimistic/iyimser olan
    @Transactional
    public Seat bookSeat(Long seatId) {
        //mevcut koltuğu kimliğe göre getirir
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("%s Id'li koltuk bulunamadı ".formatted(seatId)));

        System.out.println(Thread.currentThread().getName() + " çekilen koltuğun versiyonu: " + seat.getVersion());

        if (seat.isBooked()) {
            throw new RuntimeException("Koltuk zaten kiralanmış!");
        }
        //koltuk rezervasyonu
        seat.setBooked(true);
        //versiyon kontrolü burada gerçekleşecek
        return seatRepository.save(seat);
    }

    //pessimistic/kötümser olan
    @Transactional
    public void bookSeatWithPessimistic(Long seatId) {
        System.out.println(Thread.currentThread().getName() + " koltuğu almaya çalışıyor");

        //kötümser kilidi olan koltuğu getir
        Seat seat = seatRepository.findByIdAndLock(seatId);

        System.out.println(Thread.currentThread().getName() + " koltuk Id: " + seatId);

        if (seat.isBooked()) {
            System.out.println(Thread.currentThread().getName() + "%s Id'li koltuk zaten alınmış".formatted(seat.getId()));
            throw new RuntimeException("Koltuk zaten kiralanmış!");
        }
        //koltuk kiralama
        System.out.println(Thread.currentThread().getName() + " kiralanacak koltuk Id'si: " + seatId);

        seat.setBooked(true);
        //versyion kontrolü burada gerçekleşecek
        seatRepository.save(seat);
        System.out.println(Thread.currentThread().getName() + " %s Id'li koltuk kiralandı hayırlı olsun " + seatId);
    }
}