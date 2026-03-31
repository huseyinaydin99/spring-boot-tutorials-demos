package tr.com.huseyinaydin.dto;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public record CursorPageResponse<T>(
        List<T> data,
        int pageSize,
        Long nextCursor,
        boolean hasNext
) {
}