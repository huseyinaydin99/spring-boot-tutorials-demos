package tr.com.huseyinaydin.consul.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@RestController
@EnableConfigurationProperties(value = MyConfig.class)
public class SpringBootConsulApplication {

	@Autowired
	private MyConfig config;

	@GetMapping("/getConfigData")
	public MyConfig getConfiguration() {
		return config;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsulApplication.class, args);
	}

}