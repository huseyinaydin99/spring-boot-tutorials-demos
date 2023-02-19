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
@RequestMapping("/rest/auth")
public class ApplicationController {

	@GetMapping("/getMsg")
	public String greeting() {
		return "spring security example";
	}
}