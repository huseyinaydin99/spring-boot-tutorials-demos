package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.request.BookingRequest;
import tr.com.huseyinaydin.service.MovieBookingOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@RestController
@RequestMapping("/orchestrator")
@RequiredArgsConstructor
public class MovieBookingOrchestratorController {

    private final MovieBookingOrchestrator orchestrator;

    @PostMapping("/bookings")
    public String startSaga(@RequestBody BookingRequest request) {
        return orchestrator.createBooking(request);
    }
}
