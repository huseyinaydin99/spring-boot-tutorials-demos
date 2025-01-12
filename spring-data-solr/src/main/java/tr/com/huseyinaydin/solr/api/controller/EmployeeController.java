package tr.com.huseyinaydin.solr.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.solar.api.model.Employee;
import tr.com.huseyinaydin.solar.api.repository.EmployeeRepository;

//بسم الله الرحمن الرحيم

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
*
*/

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;

	@PostConstruct
	public void addEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Hüseyin AYDIN", new String[] { "Türkiye", "Niğde" }));
		employees.add(new Employee(2, "Buğra DOST", new String[] { "Türkiye", "Kayseri" }));
		employees.add(new Employee(3, "Murat YÜCEDAĞ", new String[] { "Türkiye", "Elazığ" }));
		repository.saveAll(employees);
	}

	@GetMapping("/getAll")
	public Iterable<Employee> getEmployees() {
		return repository.findAll();
	}

	@GetMapping("/getEmployee/{name}")
	public Employee getEmployeeByName(@PathVariable String name) {
		return repository.findByName(name);
	}
}