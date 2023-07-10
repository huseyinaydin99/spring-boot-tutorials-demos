package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import tr.com.huseyinaydin.service.FakeAPIService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Examples
*
**/

@RestController
@RequiredArgsConstructor
public class FakeApiController {

    private final FakeAPIService fakeAPIService;

    @GetMapping("user")
    public ResponseEntity<Object> getUser() {
        return ResponseEntity.ok().body(fakeAPIService.getUser());
    }

    @GetMapping("users")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(fakeAPIService.getUsers());
    }
}
