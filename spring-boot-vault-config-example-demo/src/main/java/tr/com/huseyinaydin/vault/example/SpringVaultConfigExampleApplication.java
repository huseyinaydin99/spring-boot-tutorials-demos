package tr.com.huseyinaydin.vault.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@SpringBootApplication
@EnableConfigurationProperties(Credential.class)
public class SpringVaultConfigExampleApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(SpringVaultConfigExampleApplication.class);

	private Credential credential;

	public SpringVaultConfigExampleApplication(Credential credential) {
		this.credential = credential;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringVaultConfigExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("------------properties---------");
		logger.info("Username : " + credential.getUsername());
		logger.info("Password : " + credential.getPassword());
	}
}
