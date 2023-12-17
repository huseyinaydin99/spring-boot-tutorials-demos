package tr.com.huseyinaydin.jasper.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.jasper.report.entity.Employee;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot, Jasper Reports.
* 
*/

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}