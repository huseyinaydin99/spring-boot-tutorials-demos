package tr.com.huseyinaydin.cache.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.huseyinaydin.cache.api.model.User;
import tr.com.huseyinaydin.cache.api.service.UserService;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Hazlecast Cache.
 *
 */

@RestController
@RequestMapping("/cache-api")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return service.getUsers();
	}

	@GetMapping("/getUserById/{id}")
	public User getUser(@PathVariable int id) {
		return service.getUser(id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		return service.delete(id);
	}
}