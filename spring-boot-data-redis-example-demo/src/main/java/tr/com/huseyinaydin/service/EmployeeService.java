package tr.com.huseyinaydin.service;

import java.util.List;
import java.util.Map;

import tr.com.huseyinaydin.dto.Employee;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

public interface EmployeeService {

    void save(Employee employee);
    void update(Employee employee);
    void delete(Long id);
    Employee findById(Long id);
    List<Employee> getAllEmployees(String key);
    List<Employee> findEmployeeById(List<Long> id);
    Map<Long, Employee> getAll();
}
