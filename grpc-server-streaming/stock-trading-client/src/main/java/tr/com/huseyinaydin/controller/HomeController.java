package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        System.out.println("tetiklendi");
        return "index";
    }
}