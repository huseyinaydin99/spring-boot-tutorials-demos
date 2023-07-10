package tr.com.huseyinaydin.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.entity.Employee;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
}