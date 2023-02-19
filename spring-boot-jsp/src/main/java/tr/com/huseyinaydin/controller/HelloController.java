package tr.com.huseyinaydin.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView sayHello() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "Nassın hıdır?");
		mav.addObject("currentTime", new Date());
		mav.setViewName("hello");
		return mav;
	}	
}