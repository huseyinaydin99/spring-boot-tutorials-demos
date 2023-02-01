package tr.com.huseyinaydin.service;

import java.util.List;
import java.util.Optional;

import tr.com.huseyinaydin.model.Employee;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface EmployeeService {
    Employee saveEmployee(Employee employee) throws Exception;
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}
