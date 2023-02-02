package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
public class SpringProfilesController {

	@Value("${property.my_value}")
	private String profile;

	@Autowired
	private String myBean;

	@GetMapping("/")
	public String getActiveProfile() {
		return "Active profile is :: " + profile + " <br/> <br/>Configured bean value is :: " + myBean;
	}
}