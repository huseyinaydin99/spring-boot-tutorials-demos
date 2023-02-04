package tr.com.huseyinaydin.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.entity.FileData;
import tr.com.huseyinaydin.entity.ImageData;

import java.util.Optional;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);
}
