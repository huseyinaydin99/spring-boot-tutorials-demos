package tr.com.huseyinaydin.dao;

import tr.com.huseyinaydin.dto.UserDto;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

public interface UserDao {

    int insert(UserDto userDto);
    int update(UserDto userDto);
    UserDto select(int id);
    int delete(int id);
}