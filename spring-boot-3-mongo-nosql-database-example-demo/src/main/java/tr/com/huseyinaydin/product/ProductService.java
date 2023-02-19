package tr.com.huseyinaydin.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository;

	public String save(Product product) {
		// todo use a DTO
		// todo validate the objects
		return repository.save(product).getId();
	}

	public Product findById(String id) {
		return repository.findById(id).orElse(null);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public void delete(String id) {
		repository.deleteById(id);
	}
}