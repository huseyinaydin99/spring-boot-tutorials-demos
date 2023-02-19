package tr.com.huseyinaydin.spring.security.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@RestController
@RequestMapping("/noAuth/rest")
public class NoAuthController {

	@GetMapping("/sayHi")
	public String sayHi() {
		return "hi";
	}
}