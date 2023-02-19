package tr.com.huseyinaydin.product;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class QueryMethodService {

	private final ProductRepository repository;

	public List<Product> searchByName(String name) {
		return repository.findAllByNameIgnoreCase(name);
	}

	public List<Product> searchByNameStartingWith(String name) {
		return repository.findAllByNameStartingWith(name);
	}

	public List<Product> searchByNameEndingWith(String name) {
		return repository.findAllByNameEndingWith(name);
	}

	public List<Product> searchByNameContaining(String name) {
		return repository.findAllByNameContainingIgnoreCase(name);
	}

	public List<Product> searchByPriceLt(BigDecimal price) {
		return repository.findAllByPriceLessThan(price);
	}

	public List<Product> searchByPriceGt(BigDecimal price) {
		return repository.findAllByPriceGreaterThan(price);
	}

	public List<Product> searchByPriceBetween(BigDecimal price1, BigDecimal price2) {
		return repository.findAllByPriceBetween(price1, price2);
	}

	public List<Product> searchAndSortAsc(String name) {
		return repository.findAllByNameContainingIgnoreCaseOrderByPrice(name);
	}

	public List<Product> sortAndPageByField(String name, int pageNumber, int size) {
		Pageable page = PageRequest.of(pageNumber, size);
		return repository.findAllByNameContainingIgnoreCaseOrderByPrice(name, page);
	}
}