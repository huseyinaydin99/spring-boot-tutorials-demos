package tr.com.huseyinaydin.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
public class ReactiveController {

    @GetMapping("/hello")
    public Mono<String> hello(){
        return getHello().zipWith(getName())
                .map(value -> {
                    return value.getT1() + value.getT2();
                });
    }

    public Mono<String> getHello(){
        return Mono.just("Hello ").delayElement(Duration.ofSeconds(6));
    }

    public Mono<String> getName(){
        return Mono.just("Hüseyin Aydın").delayElement(Duration.ofSeconds(6));
    }
}
