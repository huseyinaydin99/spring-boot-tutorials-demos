package tr.com.huseyinaydin.es.api.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import tr.com.huseyinaydin.es.api.model.Customer;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface CustomerRepository extends ElasticsearchRepository<Customer, String>{
	List<Customer> findByFirstname(String firstName);
}