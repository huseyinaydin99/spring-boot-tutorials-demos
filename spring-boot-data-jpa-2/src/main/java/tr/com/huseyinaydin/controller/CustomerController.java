package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/getAlien")
	public ModelAndView getCustomer(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showCustomer.jsp");
		Customer customer = repo.findById(aid).orElse(new Customer());

		System.out.println(repo.findByTech("Java"));
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByTechSorted("Java"));

		mv.addObject(customer);
		return mv;
	}
}