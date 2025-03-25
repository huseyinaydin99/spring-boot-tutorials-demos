package tr.com.huseyinaydin.service;

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
public class PessimisticSeatBookingTestService {

    @Autowired
    private MovieTicketBookingService movieTicketBookingService;

    public void testPessimisticLocking(Long seatId) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                movieTicketBookingService.bookSeatWithPessimistic(seatId);
            } catch (RuntimeException e) {
                System.out.println(Thread.currentThread().getName() + " hata: " + e.getMessage());
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                movieTicketBookingService.bookSeatWithPessimistic(seatId);
            } catch (RuntimeException e) {
                System.out.println(Thread.currentThread().getName() + " hata: " + e.getMessage());
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
