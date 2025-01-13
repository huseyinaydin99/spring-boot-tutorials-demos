package tr.com.huseyinaydin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.service.PersonService;
import tr.com.huseyinaydin.model.InputRequest;
import tr.com.huseyinaydin.model.Person;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@RestController
public class PersonController {

	@Autowired
	private PersonService service;

	@PostMapping("/savePerson")
	public String savePerson(@RequestBody InputRequest request) {
		return service.insert(request);
	}

	@GetMapping("/getPerson/{lastName}")
	public List<Person> getPerson(@PathVariable String lastName) {
		return service.getPerson(lastName);
	}

	@GetMapping("/getPersons")
	public List<Person> getPersons() {
		return service.getPersons();
	}

	@DeleteMapping("/deletePerson/{firstName}")
	public String deleteOne(@PathVariable String firstName) {
		return service.delete(firstName);
	}

	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		return service.deleteAll();
	}
}