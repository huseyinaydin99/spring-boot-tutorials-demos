package tr.com.huseyinaydin.carwasher.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tr.com.huseyinaydin.carwasher.model.Car;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Repository
@Transactional(rollbackFor = Exception.class)
public class CarWasherRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Car> getCars() {
		return entityManager.createQuery("from Car", Car.class).getResultList();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Car getCar(int id) {
		return entityManager.find(Car.class, id);
	}
}