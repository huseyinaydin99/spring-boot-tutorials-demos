package tr.com.huseyinaydin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tr.com.huseyinaydin.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Controller
public class UserController {

	@Autowired
	UserRepository userRepo;

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message;

	@RequestMapping({"/","/index"})
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "index";
	}

	@RequestMapping("/users")
	public String home(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "users";
	}

}