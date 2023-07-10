package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.AuthorRequestDto;
import tr.com.huseyinaydin.model.Author;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

public interface AuthorService {

    void add(AuthorRequestDto authorRequestDto) throws Exception;

    Author findById(Long id) throws Exception;
}