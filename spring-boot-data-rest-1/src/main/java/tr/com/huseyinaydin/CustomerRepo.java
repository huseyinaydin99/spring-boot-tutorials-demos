package tr.com.huseyinaydin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tr.com.huseyinaydin.model.Customer;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot examples
 * 
 */

@RepositoryRestResource(collectionResourceRel="customers",path="customers")
public interface CustomerRepo extends JpaRepository<Customer,Integer>{

}
