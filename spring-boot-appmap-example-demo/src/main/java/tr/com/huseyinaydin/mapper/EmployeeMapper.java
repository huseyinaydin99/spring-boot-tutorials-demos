package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tr.com.huseyinaydin.dto.EmployeeDTO;
import tr.com.huseyinaydin.entity.Employee;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends GenericMapper<Employee, EmployeeDTO> {
    @Override
    @Mapping(target = "id", ignore = true)
    Employee asEntity(EmployeeDTO dto);
}