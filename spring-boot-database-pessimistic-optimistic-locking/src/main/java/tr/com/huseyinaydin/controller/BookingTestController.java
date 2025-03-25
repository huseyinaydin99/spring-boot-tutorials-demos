package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.service.OptimisticSeatBookingTestService;
import tr.com.huseyinaydin.service.PessimisticSeatBookingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
@RequestMapping("/booking")
public class BookingTestController {

    @Autowired
    private OptimisticSeatBookingTestService optimisticSeatBookingTestService;

    @Autowired
    private PessimisticSeatBookingTestService pessimisticSeatBookingTestService;


    @GetMapping("/optimistic/{seatId}")
    public String testOptimistic(@PathVariable Long seatId) throws InterruptedException {
        optimisticSeatBookingTestService.testOptimisticLocking(seatId);
        return "İyimser kilitleme testi başlatıldı! Sonuçlar için loglara bakıver kardeşim.";
    }

    @GetMapping("/pessimistic/{seatId}")
    public String testPessimistic(@PathVariable Long seatId) throws InterruptedException {
        pessimisticSeatBookingTestService.testPessimisticLocking(seatId);
        return "Kötümser kilitleme testi başlatıldı! Sonuçlar için loglara bakıver kardeşim.";
    }
}