package tr.com.huseyinaydin.dao;

import org.springframework.data.repository.CrudRepository;

import tr.com.huseyinaydin.model.Customer;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

public interface CustomerRepo extends CrudRepository<Customer,Integer>
{

}
