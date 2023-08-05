package tr.com.huseyinaydin.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.ImageData;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 * 
 */

public interface StorageRepository extends JpaRepository<ImageData, Long> {
	Optional<ImageData> findByName(String fileName);
}