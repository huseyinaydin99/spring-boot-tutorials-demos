package tr.com.huseyinaydin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

public interface GenericService<E, M> {
    E save(E entity);
    List<E> save(List<E> entities);
    void deleteById(M id);
    Optional<E> findById(M id);
    List<E> findAll();
    Page<E> findAll(Pageable pageable);
    E update(E entity, Integer id);
}