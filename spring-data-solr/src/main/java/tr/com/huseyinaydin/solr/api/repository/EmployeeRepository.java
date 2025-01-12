package tr.com.huseyinaydin.solr.api.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import tr.com.huseyinaydin.solar.api.model.Employee;

//بسم الله الرحمن الرحيم

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
*
*/

public interface EmployeeRepository extends SolrCrudRepository<Employee, Integer>{
	Employee findByName(String name);
}