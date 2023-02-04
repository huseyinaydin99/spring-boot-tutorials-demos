package tr.com.huseyinaydin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tr.com.huseyinaydin.exception.ResourceNotFoundException;
import tr.com.huseyinaydin.model.Student;
import tr.com.huseyinaydin.repository.UserRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

// http://localhost:8080/api/v1


@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private UserRepository userRepository;

	// http://localhost:8080/api/v1/users
	@GetMapping("/users")
	public List<Student> getAllUsers() {
		return userRepository.findAll();
	}

	// http://localhost:8080/api/v1/users/1
	@GetMapping("/users/{id}")
	public ResponseEntity<Student> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Student user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found : " + userId));
		return ResponseEntity.ok().body(user);
	}

	/* 
	 http://localhost:8080/api/v1/users

	{
		"firstName": "Hüseyin",
		"lastName": "Aydın",
		"emailId": "huseyinaydin99@gmail.com",
		"createdBy": "2023-10-06T18:24:56.314+0000",
		"updatedby": "2023-10-06T18:24:56.314+0000"
	}
	*/
	@PostMapping("/users" )
	public Student createUser(@Valid @RequestBody Student user) {
		return userRepository.save(user);
	}

	
	/* 
	 http://localhost:8080/api/v1/users/1
	
	{
		"firstName": "Hüseyin",
		"lastName": "Aydın",
		"emailId": "truncatecreate@gmail.com"
	}
	*/
	@PutMapping("/users/{id}")
	public ResponseEntity<Student> updateUser(
			@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Student userDetails) throws ResourceNotFoundException {
		Student user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		user.setEmailId(userDetails.getEmailId());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setUpdatedAt(new Date());
		final Student updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	// http://localhost:8080/api/v1/users/1
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Student user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
}
