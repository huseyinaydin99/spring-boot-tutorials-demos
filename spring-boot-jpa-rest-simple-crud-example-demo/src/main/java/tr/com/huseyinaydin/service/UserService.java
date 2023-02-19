package tr.com.huseyinaydin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.model.User;
import tr.com.huseyinaydin.repository.UserRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User insertUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getUserAll() {
		return userRepository.findAll();
	}

	public ResponseEntity<User> getUserById(Long id) {
		User user = userRepository.findById(id).get();
		return ResponseEntity.ok().body(user);
	}

	public ResponseEntity<User> updateUserById(User user, Long id) {
		User userNewValue = userRepository.findById(id).get();
		userNewValue.setFirstName(user.getFirstName());
		userNewValue.setLastName(user.getLastName());
		userRepository.save(userNewValue);
		return ResponseEntity.ok().body(userNewValue);
	}

	public String deleteUserById(Long id) {
		userRepository.deleteById(id);
		return "DELETED OK";
	}

}