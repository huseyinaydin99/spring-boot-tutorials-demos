package tr.com.huseyinaydin.spring.rest.api.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import tr.com.huseyinaydin.spring.rest.advice.ServiceException;
import tr.com.huseyinaydin.spring.rest.api.dao.EmployeeDao;
import tr.com.huseyinaydin.spring.rest.api.model.Employee;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Path("/employeeResource")
public class EmployeeResource {

	@Autowired
	private EmployeeDao dao;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/save")
	public Employee saveEmployee(Employee employee) throws ServiceException {
		if (employee.getName() == null || employee.getName().isEmpty()) {
			throw new ServiceException("Name should not be empty or null", HttpStatus.BAD_REQUEST.value());
		} else {
			return dao.save(employee);
		}
	}

	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getAllEmployees")
	public List<Employee> getAllEMployees() {
		return dao.findAll();
	}

	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getEmployee/{name}")
	public Employee getEmployeeByName(@PathParam("name") String name) throws ServiceException {
		Employee employee = dao.findByName(name);
		if (employee == null) {
			throw new ServiceException("Employee Not Found : " + name, HttpStatus.CONFLICT.value());
		}
		return employee;
	}

}
