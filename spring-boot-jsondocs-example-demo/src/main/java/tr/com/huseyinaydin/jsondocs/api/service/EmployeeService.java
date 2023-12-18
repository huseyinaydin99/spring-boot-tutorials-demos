package tr.com.huseyinaydin.jsondocs.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.jsondocs.api.dao.EmployeeRepository;
import tr.com.huseyinaydin.jsondocs.api.model.Employee;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot JSONDocs.
* 
*/

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public String saveEmployee(Employee employee) {
		repository.save(employee);
		return "Yeni çalışan kaydı yapıldı ahanda Id'si :" + employee.getId();
	}

	public Employee getEmployee(int id) {
		return repository.findOne(id);
	}

	public List<Employee> deleteEmployee(int id) {
		repository.delete(id);
		return repository.findAll();
	}
}