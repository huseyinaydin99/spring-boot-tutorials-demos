package tr.com.huseyinaydin.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.ps.api.entity.Payment;
import tr.com.huseyinaydin.ps.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository repository;
    
    Logger logger= LoggerFactory.getLogger(PaymentService.class);
    
    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        logger.info("Ödeme servisi isteği: {}", new ObjectMapper().writeValueAsString(payment));

        return repository.save(payment);
    }

    public String paymentProcessing(){
        //api should be 3rd party payment gateway (paypal,paytm...)
        return new Random().nextBoolean() ? "success" : "false";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment=repository.findByOrderId(orderId);
        logger.info("Ödeme servisi findPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));
        return payment ;
    }
}