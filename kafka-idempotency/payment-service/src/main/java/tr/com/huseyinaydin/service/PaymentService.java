package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.OrderDto;
import tr.com.huseyinaydin.entity.Payment;
import tr.com.huseyinaydin.respository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public void processPayment(OrderDto orderDto) {
        // payment gateway call (deduct amount from user account)
        Payment payment = new Payment(null,
                orderDto.getRequestId(),
                orderDto.getOrderId(),
                orderDto.getPrice() * orderDto.getQuantity(),
                LocalDateTime.now());

        repository.save(payment);
    }
}