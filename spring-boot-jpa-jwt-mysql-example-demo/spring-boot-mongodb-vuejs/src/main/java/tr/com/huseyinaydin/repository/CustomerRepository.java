package tr.com.huseyinaydin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tr.com.huseyinaydin.model.Customer;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface CustomerRepository extends MongoRepository<Customer, String>{
	List<Customer> findByAge(int age);
}