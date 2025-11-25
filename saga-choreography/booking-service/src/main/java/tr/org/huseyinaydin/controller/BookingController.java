package tr.org.huseyinaydin.controller;

import tr.org.huseyinaydin.request.BookingRequest;
import tr.org.huseyinaydin.response.BookingResponse;
import tr.org.huseyinaydin.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-service")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Film için koltuk rezervasyon taleplerini işleyen endpoint
    // Film için koltuk rezervasyon isteklerini karşılayan uç nokta
    @PostMapping("/bookSeat")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookSeats(request);
        return ResponseEntity.ok(response);
    }
}