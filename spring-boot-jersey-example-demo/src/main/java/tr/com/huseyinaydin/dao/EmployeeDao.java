package tr.com.huseyinaydin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.model.Employee;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	Employee findByName(String name);
}