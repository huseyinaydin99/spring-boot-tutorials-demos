package tr.com.huseyinaydin.cassandra.api.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.com.huseyinaydin.cassandra.api.model.User;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public interface UserRepository extends ReactiveCassandraRepository<User, Integer> {

	@AllowFiltering
	Flux<User> findByAgeLessThan(int age);

	@AllowFiltering
	Mono<User> findByAddress(String address);

}
