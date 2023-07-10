package tr.com.huseyinaydin.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.dto.EmployeeDTO;
import tr.com.huseyinaydin.entity.Employee;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-07T22:07:52+0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220802-0458, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO asDTO(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( entity.getId() );
        employeeDTO.setName( entity.getName() );
        employeeDTO.setDept( entity.getDept() );
        employeeDTO.setSalary( entity.getSalary() );

        return employeeDTO;
    }

    @Override
    public List<Employee> asEntityList(List<EmployeeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( dtoList.size() );
        for ( EmployeeDTO employeeDTO : dtoList ) {
            list.add( asEntity( employeeDTO ) );
        }

        return list;
    }

    @Override
    public List<EmployeeDTO> asDTOList(List<Employee> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( entityList.size() );
        for ( Employee employee : entityList ) {
            list.add( asDTO( employee ) );
        }

        return list;
    }

    @Override
    public Employee asEntity(EmployeeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setName( dto.getName() );
        employee.setDept( dto.getDept() );
        employee.setSalary( dto.getSalary() );

        return employee;
    }
}
