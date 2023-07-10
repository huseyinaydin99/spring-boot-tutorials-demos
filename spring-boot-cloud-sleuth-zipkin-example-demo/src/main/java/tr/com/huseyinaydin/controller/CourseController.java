package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.request.CourseFilterRequest;
import tr.com.huseyinaydin.dto.request.CourseRequest;
import tr.com.huseyinaydin.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/courses", "api/courses"})
public class CourseController {

    private final CourseService courseService;
    
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody  CourseRequest courseRequest){
        log.info("Intercept create record {}", courseRequest);
        courseService.create(courseRequest);
        return ResponseEntity.ok("Record Created!");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody CourseRequest courseRequest,
                                         @PathVariable Long id) {
        log.info("Intercept update record {}", courseRequest);
        courseService.update(id, courseRequest);
        return ResponseEntity.ok("Record Updated!");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Intercept delete record {}", id);
        courseService.delete(id);
        return ResponseEntity.ok("Record Deleted!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        log.info("Intercept get record by id {}", id);
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Object> list(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/pagination")
    public ResponseEntity<Object> listWithPagination(CourseFilterRequest filterRequest) {
        log.info("Intercept list with pagination record {}", filterRequest);
        return ResponseEntity.ok(courseService.filterWithPagination(filterRequest));
    }
}
