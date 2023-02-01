package tr.com.huseyinaydin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.model.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// http://localhost:8080/employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
}