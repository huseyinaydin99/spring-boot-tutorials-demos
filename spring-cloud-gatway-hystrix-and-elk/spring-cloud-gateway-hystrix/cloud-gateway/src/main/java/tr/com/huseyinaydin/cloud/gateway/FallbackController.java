package tr.com.huseyinaydin.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack() {
        return Mono.just("Sipariş mikro hizmeti şu an yanıt veremiyor teknik bir aksaklık nedeni ile. Daha sonra gel tekrardan deneyiver.");
    }
    @RequestMapping("/paymentFallback")
    public Mono<String> paymentServiceFallBack() {
        return Mono.just("Ödeme mikro hizmeti şu an yanıt veremiyor teknik bir aksaklık nedeni ile. Daha sonra gel tekrardan deneyiver.");
    }
}