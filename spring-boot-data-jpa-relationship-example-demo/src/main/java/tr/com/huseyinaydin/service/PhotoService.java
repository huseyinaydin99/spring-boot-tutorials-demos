package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.PhotoRequestDto;
import tr.com.huseyinaydin.model.Photo;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

public interface PhotoService {

    void addNewPhoto(PhotoRequestDto photoRequestDto) throws Exception;

    Photo checkById(Long id);
}