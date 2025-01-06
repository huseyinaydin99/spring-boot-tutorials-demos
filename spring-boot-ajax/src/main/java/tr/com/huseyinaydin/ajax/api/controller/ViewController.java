package tr.com.huseyinaydin.ajax.api.controller;

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
public class ViewController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}
}