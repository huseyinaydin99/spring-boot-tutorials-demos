package tr.com.huseyinaydin.cache.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.cache.api.dao.UserRepository;
import tr.com.huseyinaydin.cache.api.model.User;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Hazlecast Cache.
 *
 */

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Cacheable(cacheNames = { "userCache" })
	public List<User> getUsers() {
		System.out.println("getUsers() metodunun geri dönderdiği liste cachelendi.");
		return repository.findAll();
	}

	@Cacheable(value = "userCache", key = "#id", unless = "#result==null")
	public User getUser(int id) {
		System.out.println("getUsers() metodunun geri dönderdiği User nesnesi cachelendi.");
		return repository.findOne(id);
	}

	@CacheEvict(value = "userCache")
	public String delete(int id) {
		repository.delete(id);
		return "User nesnesi cache'den silindi. " + id;
	}
}