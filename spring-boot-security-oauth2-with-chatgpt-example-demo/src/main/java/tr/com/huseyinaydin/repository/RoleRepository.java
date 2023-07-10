package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}