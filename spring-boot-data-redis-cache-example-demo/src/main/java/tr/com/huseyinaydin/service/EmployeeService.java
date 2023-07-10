package tr.com.huseyinaydin.service;

import java.util.List;

import tr.com.huseyinaydin.dto.EmployeeDto;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

public interface EmployeeService {

    EmployeeDto findByEmployeeNo(String empNo);

    List<EmployeeDto> getAll();

    EmployeeDto update(EmployeeDto employeeDto, String empNo);

    void delete(String empNo);
}