package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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

    @GetMapping
    public String index(Principal principal, Model model) {
        model.addAttribute("user", principal.getName());
        return "index";
    }

    @GetMapping("/ott/sent")
    public String sentOTT(){
        return "ott-sent";
    }
}