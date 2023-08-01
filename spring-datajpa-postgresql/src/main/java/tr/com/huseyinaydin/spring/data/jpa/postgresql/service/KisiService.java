package tr.com.huseyinaydin.spring.data.jpa.postgresql.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tr.com.huseyinaydin.spring.data.jpa.postgresql.dto.KisiDto;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

public interface KisiService {
    KisiDto save(KisiDto kisiDto);
    void delete(Long id);
    List<KisiDto> getAll();
    Page<KisiDto> getAll(Pageable pageable);
}