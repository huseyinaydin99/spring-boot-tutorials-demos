package tr.com.huseyinaydin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Configuration
public class ProfileConfig {

	@Profile("DEV")
	@Bean(name = "myBean")
	public String createDevBean() {
		return "My bean is configured with Development configurations!!";
	}

	@Profile("PROD")
	@Bean(name = "myBean")
	public String createProdBean() {
		return "My bean is configured with Production configurations!!";
	}
}