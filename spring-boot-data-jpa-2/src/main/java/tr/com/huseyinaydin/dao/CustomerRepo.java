package tr.com.huseyinaydin.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tr.com.huseyinaydin.model.Customer;

import java.util.List;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot examples
 * 
 */

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	List<Customer> findByTech(String tech);

	List<Customer> findByAidGreaterThan(int aid);

	@Query("from Customer where tech=?1 order by aname")
	List<Customer> findByTechSorted(String tech);

}
