package tr.com.huseyinaydin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@SpringBootApplication
public class SpringBeanScopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBeanScopeApplication.class, args);
	}


	@Autowired
	private Message facebookMessenger;
	
	@Autowired
	private Message telegramMessenger;

	@Bean
	void testBeanScope() {
		facebookMessenger.setMessageBot("Hi! the message from Facebook Messenger Bot!");
		telegramMessenger.setMessageBot("Hi! the message from Telegram Messenger Bot!");

		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Facebook Messenger Bot: " + facebookMessenger.getMessageBot());
		System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Telegram Messenger Bot: " + telegramMessenger.getMessageBot() +
				ConsoleColors.RESET);
	}
}