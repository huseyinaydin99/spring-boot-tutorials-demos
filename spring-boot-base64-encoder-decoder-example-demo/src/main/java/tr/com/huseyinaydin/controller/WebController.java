package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Controller
public class WebController {
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
}