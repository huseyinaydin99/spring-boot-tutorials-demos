package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
@Controller
public class RealtimeNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeNotificationApplication.class, args);
	}

	@GetMapping("/admin")
	public String admin(){
		return "order";
	}

	@GetMapping("/user")
	public String user(){
		return "order-user-client";
	}

	@GetMapping("/index")
	public String index(){
		return "index";
	}

	@GetMapping("/client")
	public String indexUser(){
		return "index_user";
	}
}