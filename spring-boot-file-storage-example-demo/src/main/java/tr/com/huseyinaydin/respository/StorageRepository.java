package tr.com.huseyinaydin.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.ImageData;

import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String fileName);
}
