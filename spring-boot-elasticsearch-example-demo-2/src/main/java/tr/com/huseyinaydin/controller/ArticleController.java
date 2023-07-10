package tr.com.huseyinaydin.controller;

import lombok.RequiredArgsConstructor;
import tr.com.huseyinaydin.model.Article;
import tr.com.huseyinaydin.repository.ArticleRepository;

import org.elasticsearch.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping(value = { "api/articles" })
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

	private final ArticleRepository articleRepository;

	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<Object> create(@RequestBody Article article) {
		try {
			articleRepository.save(article);
			return ResponseEntity.status(HttpStatus.CREATED).body("Create Successfully");
		} catch (Exception e) {
			if (!e.getMessage().contains("Created")) {
				throw e;
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Created");
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> retrieve(@PathVariable Long id) {
		return ResponseEntity
				.ok(articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID: " + id)));
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> retrieve() {
		return ResponseEntity.ok(articleRepository.findAll());
	}

	@PutMapping(value = "/", consumes = "application/json")
	public ResponseEntity<Object> update(@RequestBody Article article) {
		try {
			return ResponseEntity.ok(articleRepository.save(article));
		} catch (Exception e) {
			if (!e.getMessage().contains("200 OK")) {
				e.getLocalizedMessage();
				throw e;
			}
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			articleRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
		} catch (Exception e) {
			if (!e.getMessage().contains("200")) {
				e.getLocalizedMessage();
				throw e;
			}
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
	}
}