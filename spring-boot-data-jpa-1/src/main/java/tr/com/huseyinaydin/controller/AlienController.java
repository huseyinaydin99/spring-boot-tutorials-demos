package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import tr.com.huseyinaydin.dao.CustomerRepo;
import tr.com.huseyinaydin.model.Customer;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@Controller
public class AlienController
{
	@Autowired
	CustomerRepo repo;

	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@RequestMapping("/addAlien")
	public String addAlien(Customer alien)
	{
		repo.save(alien);
		return "home.jsp";
	}
}
