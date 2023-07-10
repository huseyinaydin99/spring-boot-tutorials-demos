package tr.com.huseyinaydin.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.EmployeeDTO;
import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.mapper.EmployeeMapper;
import tr.com.huseyinaydin.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeControllerImpl.class)
class EmployeeControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService mockEmployeeService;
    @MockBean
    private EmployeeMapper mockEmployeeMapper;

    @Test
    void testSave() throws Exception {
        // Setup
        when(mockEmployeeMapper.asEntity(any(EmployeeDTO.class))).thenReturn(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeService.save(any(Employee.class))).thenReturn(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeMapper.asDTO(any(Employee.class))).thenReturn(new EmployeeDTO(0, "name", "dept", 0.0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/employee")
                        .content(new ObjectMapper().writeValueAsString(new EmployeeDTO(0, "name", "dept", 0.0))).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure EmployeeService.findById(...).
        final Optional<Employee> employee = Optional.of(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeService.findById(0)).thenReturn(employee);

        when(mockEmployeeMapper.asDTO(any(Employee.class))).thenReturn(new EmployeeDTO(0, "name", "dept", 0.0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testFindById_EmployeeServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockEmployeeService.findById(0)).thenReturn(Optional.empty());
        when(mockEmployeeMapper.asDTO(any(Employee.class))).thenReturn(new EmployeeDTO(0, "name", "dept", 0.0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/employee/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(mockEmployeeService).deleteById(0);
    }

    @Test
    void testList() throws Exception {
        // Setup
        // Configure EmployeeService.findAll(...).
        final List<Employee> employees = Arrays.asList(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeService.findAll()).thenReturn(employees);

        // Configure EmployeeMapper.asDTOList(...).
        final List<EmployeeDTO> employeeDTOS = Arrays.asList(new EmployeeDTO(0, "name", "dept", 0.0));
        when(mockEmployeeMapper.asDTOList(Arrays.asList(new Employee(0, "name", "dept", 0.0))))
                .thenReturn(employeeDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testList_EmployeeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEmployeeService.findAll()).thenReturn(Collections.emptyList());

        // Configure EmployeeMapper.asDTOList(...).
        final List<EmployeeDTO> employeeDTOS = Arrays.asList(new EmployeeDTO(0, "name", "dept", 0.0));
        when(mockEmployeeMapper.asDTOList(Arrays.asList(new Employee(0, "name", "dept", 0.0))))
                .thenReturn(employeeDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testList_EmployeeMapperReturnsNoItems() throws Exception {
        // Setup
        // Configure EmployeeService.findAll(...).
        final List<Employee> employees = Arrays.asList(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeService.findAll()).thenReturn(employees);

        when(mockEmployeeMapper.asDTOList(Arrays.asList(new Employee(0, "name", "dept", 0.0))))
                .thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testPageQuery() throws Exception {
        // Setup
        // Configure EmployeeService.findAll(...).
        final Page<Employee> employees = new PageImpl<>(Arrays.asList(new Employee(0, "name", "dept", 0.0)));
        when(mockEmployeeService.findAll(any(Pageable.class))).thenReturn(employees);

        when(mockEmployeeMapper.asDTO(any(Employee.class))).thenReturn(new EmployeeDTO(0, "name", "dept", 0.0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee/page-query")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testPageQuery_EmployeeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEmployeeService.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        when(mockEmployeeMapper.asDTO(any(Employee.class))).thenReturn(new EmployeeDTO(0, "name", "dept", 0.0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/employee/page-query")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        when(mockEmployeeMapper.asEntity(any(EmployeeDTO.class))).thenReturn(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeService.update(any(Employee.class), eq(0))).thenReturn(new Employee(0, "name", "dept", 0.0));
        when(mockEmployeeMapper.asDTO(any(Employee.class))).thenReturn(new EmployeeDTO(0, "name", "dept", 0.0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/api/employee/{id}", 0)
                        .content(new ObjectMapper().writeValueAsString(new EmployeeDTO(0, "name", "dept", 0.0))).contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
