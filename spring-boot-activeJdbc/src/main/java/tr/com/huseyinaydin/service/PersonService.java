package tr.com.huseyinaydin.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

@Service
public class PersonService {

	public String insert(InputRequest request) {
		Person person = new Person();
		person.set("id", request.getId());
		person.set("first_name", request.getFirstName());
		person.set("last_name", request.getLastName());
		boolean flag = person.insert();
		return "Kayıt girildi : " + flag;
	}

	public List<Person> getPerson(String lastName) {
		List<Person> persons = Person.where("last_name=?", lastName);
		System.out.println(persons);
		return persons;
	}

	public List<Person> getPersons() {
		return Person.findAll();
	}

	public String delete(String firstName) {
		int no = Person.delete("first_name=?", firstName);
		return no + " nolu kayıt silindi!";
	}

	public String deleteAll() {
		return "silindi: " + Person.deleteAll();
	}
}