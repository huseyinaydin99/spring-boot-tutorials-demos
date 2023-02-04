package tr.com.huseyinaydin.es.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.es.api.model.Customer;
import tr.com.huseyinaydin.es.api.service.QueryDSLService;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@RestController
public class QueryDSLController {

	@Autowired
	private QueryDSLService service;

	@GetMapping("/serachMultiField/{firstname}/{age}")
	public List<Customer> serachByMultiField(@PathVariable String firstname, @PathVariable int age) {
		return service.searchMultiField(firstname, age);
	}

	@GetMapping("/customSearch/{firstName}")
	public List<Customer> getCustomerByField(@PathVariable String firstName) {
		return service.getCustomerSerachData(firstName);
	}

	@GetMapping("/search/{text}")
	public List<Customer> doMultimatchQuery(@PathVariable String text) {
		return service.multiMatchQuery(text);
	}
}