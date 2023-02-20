package tr.com.huseyinaydin.spring.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.spring.api.entity.Person;
import tr.com.huseyinaydin.spring.api.repository.PersonRepository;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public Person savePerson(Person person) {
		System.out.println("Service savePerson() method Called...");
		return repository.save(person);
	}
	
	public List<Person> findAllPersons() {
		System.out.println("Service findAllPersons() method Called...");
		return repository.findAll();
	}
}