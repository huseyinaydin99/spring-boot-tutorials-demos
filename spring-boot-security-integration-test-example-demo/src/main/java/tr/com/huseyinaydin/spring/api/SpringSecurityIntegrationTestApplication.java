package tr.com.huseyinaydin.spring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.spring.api.entity.Person;
import tr.com.huseyinaydin.spring.api.service.PersonService;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@SpringBootApplication
@RestController
public class SpringSecurityIntegrationTestApplication {

	@Autowired
	private PersonService service;

	@GetMapping("/getAllPersons")
	public List<Person> getPersons() {
		System.out.println("Controller getPersons() method called...");
		return service.findAllPersons();
	}

	@PostMapping("/savePerson")
	public Person savePerson(@RequestBody Person person) {
		System.out.println("Controller savePerson() method called...");
		return service.savePerson(person);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityIntegrationTestApplication.class, args);
	}
}