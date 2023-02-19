package tr.com.huseyinaydin.category;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface CategoryRepository extends MongoRepository<Category, String> {

}
