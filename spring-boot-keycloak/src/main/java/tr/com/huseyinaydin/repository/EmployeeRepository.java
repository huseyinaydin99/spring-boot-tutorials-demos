package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}