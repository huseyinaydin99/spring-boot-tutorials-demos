package tr.com.huseyinaydin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	// http://localhost:8085/api/v1/message
	@GetMapping("/message")
	public String getMessage() {
		return "Welcome to Spring Boot with Docker App Running!";
	}
}