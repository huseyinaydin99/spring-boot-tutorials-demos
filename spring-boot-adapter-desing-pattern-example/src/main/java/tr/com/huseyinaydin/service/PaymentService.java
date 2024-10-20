package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.processor.PaymentProcessor;
import tr.com.huseyinaydin.request.PaymentRequest;
import tr.com.huseyinaydin.response.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class PaymentService {

    private Map<String, PaymentProcessor> paymentProcessorMap;

    public PaymentService(List<PaymentProcessor> paymentProcessors) {
        paymentProcessorMap = paymentProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getClass().getSimpleName(), Function.identity()));
        /*
        Function.identity() listedeki her bir elemanın kendisini döndürür.
        Yani, listedeki öğeler üzerinde herhangi bir değişiklik yapmadan,
        öğelerin doğrudan kendisini haritalama işlemi için kullanır.
        */
    }

    public PaymentResponse processPayment(String gateway, PaymentRequest paymentRequest) {
        PaymentProcessor paymentProcessor = paymentProcessorMap.get(gateway + "Adapter");
        paymentProcessor.makePayment(paymentRequest.amount());
        return new PaymentResponse(true, new Random().nextLong(1000000000L));
    }


//    private PaymentProcessor getPaymentGatewayInstance(String gateway) {
//        PaymentProcessor processor = null;
//        switch (gateway.toLowerCase()) {
//            case "paypal":
//                processor = new PayPalAdapter();
//                break;
//            case "stripe":
//                processor = new StripeAdapter();
//                break;
//            case "gpay":
//                processor = new GPayAdapter();
//                break;
//        }
//        return processor;
//    }
}