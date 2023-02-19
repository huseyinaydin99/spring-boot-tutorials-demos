package tr.com.huseyinaydin.spring.kotlin.restful.api.controller

import tr.com.huseyinaydin.spring.kotlin.restful.api.dto.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
 *
 **/

@RestController
class UserController {

	@GetMapping("/getUser")
	fun getUser() = User(571, "Muhammed", 99.999)

	@GetMapping("/getUsers")
	fun getUsers(): List<User> {
		val users = listOf(User(111, "Hüseyin", 20.000), User(999, "Ahmet", 40.000))
		return users;
	}
}