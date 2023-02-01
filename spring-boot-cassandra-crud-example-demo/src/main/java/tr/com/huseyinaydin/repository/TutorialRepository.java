package tr.com.huseyinaydin.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import tr.com.huseyinaydin.model.Tutorial;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface TutorialRepository extends CassandraRepository<Tutorial, UUID> {

	@AllowFiltering
	List<Tutorial> findByPublished(boolean published);

	List<Tutorial> findByTitleContaining(String title);
}
