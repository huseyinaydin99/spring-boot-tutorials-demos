package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
}