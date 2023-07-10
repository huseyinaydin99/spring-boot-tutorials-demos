package tr.com.huseyinaydin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tr.com.huseyinaydin.dto.EmployeeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Api(tags = "Employee API")
public interface EmployeeController {
    @ApiOperation("Add new data")
    public EmployeeDTO save(@RequestBody EmployeeDTO employee);

    @ApiOperation("Find by Id")
    public EmployeeDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<EmployeeDTO> list();

    @ApiOperation("Pagination request")
    public Page<EmployeeDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public EmployeeDTO update(@RequestBody EmployeeDTO dto, @PathVariable("id") Integer id);
}