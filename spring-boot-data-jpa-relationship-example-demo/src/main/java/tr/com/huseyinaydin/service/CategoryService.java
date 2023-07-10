package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CategoryRequestDto;
import tr.com.huseyinaydin.model.Category;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

public interface CategoryService {

    void createNewCategory(CategoryRequestDto categoryRequestDto) throws Exception;

    Category checkById(Long id) throws Exception;
}