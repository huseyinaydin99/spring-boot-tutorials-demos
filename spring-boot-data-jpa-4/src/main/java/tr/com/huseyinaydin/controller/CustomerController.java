package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addCustomer")
	public String addCustomer(Customer customer) {
		repo.save(customer);
		return "home.jsp";
	}

	@RequestMapping("/customers")
	@ResponseBody
	public List<Customer> getCustomers() {
		return repo.findAll();
	}

	@RequestMapping("/customer/{aid}")
	@ResponseBody
	public Optional<Customer> getCustomer(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
}