package tr.com.huseyinaydin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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

public interface CustomerRepo extends JpaRepository<Customer,Integer>{

}
