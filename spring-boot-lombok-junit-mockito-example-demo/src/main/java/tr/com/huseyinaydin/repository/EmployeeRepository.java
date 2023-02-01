package tr.com.huseyinaydin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.model.Employee;

import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}