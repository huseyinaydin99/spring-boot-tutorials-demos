package tr.com.huseyinaydin.authcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Keycloak.
 *
 */

@RestController
public class HomeRestController {
    @GetMapping("/merhaba")
    public String hello() {
        return "selamun aleyküm kardeş";
    }
}
