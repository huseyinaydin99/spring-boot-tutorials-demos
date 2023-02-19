package tr.com.huseyinaydin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tr.com.huseyinaydin.model.Address;
import tr.com.huseyinaydin.model.User;
import tr.com.huseyinaydin.repository.AddressRepository;
import tr.com.huseyinaydin.repository.UserRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(UserRepository userRepository, AddressRepository addressRepository) {
		return args -> {

			// create a user instance
			User user = new User("Kinomia Takao", "takao.kinomia@beyblade.com", "toor");

			// create an address instance
			Address address = new Address("Tinkivinki", "Türkiye", "Niğde", "123", "TR");

			// set child reference
			address.setUser(user);

			// set parent reference
			user.setAddress(address);

			// save the parent
			// which will save the child (address) as well
			userRepository.save(user);
		};
	}
}