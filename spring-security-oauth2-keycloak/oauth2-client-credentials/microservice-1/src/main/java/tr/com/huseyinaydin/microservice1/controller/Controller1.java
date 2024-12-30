package tr.com.huseyinaydin.microservice1.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Keycloak.
 *
 */

@RestController
public class Controller1 {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); //microservice'ler araıs istemci
    private final WebClient webClient = WebClient.builder().build();

    @GetMapping("/microservice1/home")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8084/microservice2/home", HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return "Selamlar burası Microservice 1! " + response.getBody();
    }

    @GetMapping("microservice1/home/webclient")
    @ResponseStatus(HttpStatus.OK)
    public String helloWebClient(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String response = webClient.get()
                .uri("http://localhost:8084/microservice2/home")
                .headers(header -> header.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Merhabalar - burası Microservice 1! " + response;
    }
}