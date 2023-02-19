package tr.com.huseyinaydin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot examples
 * 
 */

@Controller
public class HomeController {
	
	@RequestMapping("home")
	public String home() {
		System.out.println("hi");
		return "home.jsp";
	}
}