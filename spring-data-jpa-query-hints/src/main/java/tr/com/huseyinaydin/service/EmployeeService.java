package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Long fetchEmployees(){
        return repository.count();
    }

    public List<Employee> getEmployeesBySalary(double salary){
        return repository.findEmployeesWithSalaryGreaterThan(salary);
    }
}