package tr.com.huseyinaydin.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.dao.EmployeeRepository;
import tr.com.huseyinaydin.dto.EmployeeDTO;
import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.mapper.EmployeeMapper;
import tr.com.huseyinaydin.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee entity) {
        return repository.save(entity);
    }

    @Override
    public List<Employee> save(List<Employee> entities) {
        return (List<Employee>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) repository.findAll();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        Page<Employee> entityPage = repository.findAll(pageable);
        List<Employee> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Employee update(Employee entity, Integer id) {
        Optional<Employee> optional = findById(id) ;
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}