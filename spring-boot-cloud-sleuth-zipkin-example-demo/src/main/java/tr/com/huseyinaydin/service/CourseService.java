package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.request.CourseFilterRequest;
import tr.com.huseyinaydin.dto.request.CourseRequest;
import tr.com.huseyinaydin.dto.response.CourseResponse;
import tr.com.huseyinaydin.dto.response.CourseResponsePagination;

import java.util.List;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

public interface CourseService {

    void create(CourseRequest courseRequest);
    void update(Long id, CourseRequest courseRequest);
    void delete(Long id);
    CourseResponse getById(Long id);
    List<CourseResponse> getAllCourses();
    CourseResponsePagination filterWithPagination(CourseFilterRequest filterRequest);
}