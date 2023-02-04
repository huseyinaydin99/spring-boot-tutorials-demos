package tr.com.huseyinaydin;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tr.com.huseyinaydin.dao.EmployeeDao;
import tr.com.huseyinaydin.model.Employee;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@SpringBootApplication
public class SpringRestJerseyExampleApplication {
	
	@Autowired
	private EmployeeDao dao;
	
	@PostConstruct
	public void initDB() {
		dao.saveAll(Stream.of(new Employee(890,"Hüseyin","DEV"),new Employee(680,"Sami","QA"),new Employee(143,"John","HR")).collect(Collectors.toList()));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRestJerseyExampleApplication.class, args);
	}

}
