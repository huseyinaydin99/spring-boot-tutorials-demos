package tr.com.huseyinaydin.authcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Keycloak.
 *
 */

@Controller
public class HomeController {

    @GetMapping("/selam")
    public String home() {
        return "home";
    }
}