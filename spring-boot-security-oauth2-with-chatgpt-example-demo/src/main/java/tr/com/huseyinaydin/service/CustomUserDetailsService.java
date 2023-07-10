package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Role;
import tr.com.huseyinaydin.model.User;
import tr.com.huseyinaydin.model.UserRole;
import tr.com.huseyinaydin.repository.RoleRepository;
import tr.com.huseyinaydin.repository.UserRepository;
import tr.com.huseyinaydin.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            Role role = roleRepository.findById(userRole.getRole().getId()).get();
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
