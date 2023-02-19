package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tr.com.huseyinaydin.dao.CustomerRepo;
import tr.com.huseyinaydin.model.Customer;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot examples
 * 
 */

@RestController
public class CustomerController {
	@Autowired
	CustomerRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@DeleteMapping("/customer/{aid}")
	public String deleteCustomer(@PathVariable int aid) {
		Customer a = repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}

	@PostMapping(path = "/alien", consumes = { "application/json" })
	public Customer addCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return customer;
	}

	@GetMapping(path = "/customers")
	public List<Customer> getCustomers() {
		return repo.findAll();
	}

	@PutMapping(path = "/customer", consumes = { "application/json" })
	public Customer saveOrUpdateCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return customer;
	}

	@RequestMapping("/alien/{aid}")
	public Optional<Customer> getCustomer(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
}