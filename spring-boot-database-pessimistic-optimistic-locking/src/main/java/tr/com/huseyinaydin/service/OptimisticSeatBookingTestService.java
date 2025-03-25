package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class OptimisticSeatBookingTestService {

    @Autowired
    private MovieTicketBookingService movieTicketBookingService;

    public void testOptimisticLocking(Long seatId) throws InterruptedException {
        // 2 thread - 2 kanal/akış
        Thread th1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " koltuğu rezerve etmeye çalışıyor");
                Seat seat = movieTicketBookingService.bookSeat(seatId);
                System.out.println(Thread.currentThread().getName() + " kiralanmış koltuk versiyonu: " + seat.getVersion());
            } catch (Exception ex) {
                System.out.println(Thread.currentThread().getName() + " hata oldu: " + ex.getMessage());
            }
        });

        Thread th2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " koltuğu rezerve etmeye çalışıyor");
                Seat seat = movieTicketBookingService.bookSeat(seatId);
                System.out.println(Thread.currentThread().getName() + " kiralanmış koltuk versiyonu: " + seat.getVersion());
            } catch (Exception ex) {
                System.out.println(Thread.currentThread().getName() + " hata oldu: " + ex.getMessage());
            }
        });

        th1.start();
        th2.start();
        th1.join();
        th2.join();
    }
}
