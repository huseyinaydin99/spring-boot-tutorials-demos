package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.BookRequestDto;
import tr.com.huseyinaydin.dto.BookResponseDto;

import java.util.List;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

public interface BookService {
    void createNewBook(BookRequestDto bookRequestDto) throws Exception;
    List<BookResponseDto> listBooks() throws Exception;
}