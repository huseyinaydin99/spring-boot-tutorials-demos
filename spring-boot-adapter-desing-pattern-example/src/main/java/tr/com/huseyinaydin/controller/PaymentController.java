package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.request.PaymentRequest;
import tr.com.huseyinaydin.response.PaymentResponse;
import tr.com.huseyinaydin.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest,
                                                       @RequestParam String gateway) {
        log.debug("Ödeme isteği alındı hacım: {}", paymentRequest);
        return ResponseEntity.ok(paymentService.processPayment(gateway, paymentRequest));
    }
}