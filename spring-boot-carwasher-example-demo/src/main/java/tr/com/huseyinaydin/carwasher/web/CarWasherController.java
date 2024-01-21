package tr.com.huseyinaydin.carwasher.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tr.com.huseyinaydin.carwasher.dao.jpa.CarWasherRepository;
import tr.com.huseyinaydin.carwasher.model.Car;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@RestController
@RequestMapping("/carWasherController")
public class CarWasherController {
	
	@Autowired
	private CarWasherRepository repository;
	
	@RequestMapping("/cacheBustingTest")
	public ModelAndView cacheBustinTest() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cacheBustingTest");
		return modelAndView;
	}
	
	@RequestMapping("/car/{id}")
	public Car getCar(@PathVariable int id) {
		return repository.getCar(id);
	}
	
	@RequestMapping("/cars")
	public List<Car> getCars() {
		return repository.getCars();
	}
}