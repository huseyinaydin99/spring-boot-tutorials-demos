package tr.com.huseyinaydin.spring.jdbi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.spring.jdbi.api.model.Order;
import tr.com.huseyinaydin.spring.jdbi.api.repository.OrderRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
@RestController
public class SpringJdbiExampleApplication {

	@Autowired
	private OrderRepository repository;

	@PostMapping("/addOrder")
	public String addOrder(@RequestBody Order order) {
		Integer count = repository.addOrder(order);
		return count + " record saved...";
	}

	@GetMapping("/getOrders")
	public List<Order> getOrders() {
		return repository.findAllOrders();
	}

	@GetMapping("/getOrders/{price}")
	public List<Order> getOrdersByPrice(@PathVariable int price) {
		return repository.getOrderByPrice(price);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbiExampleApplication.class, args);
	}
}