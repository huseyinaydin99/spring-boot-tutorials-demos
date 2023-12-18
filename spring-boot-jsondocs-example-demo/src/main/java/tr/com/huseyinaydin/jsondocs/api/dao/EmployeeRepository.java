package tr.com.huseyinaydin.jsondocs.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.jsondocs.api.model.Employee;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot JSONDocs.
* 
*/

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}