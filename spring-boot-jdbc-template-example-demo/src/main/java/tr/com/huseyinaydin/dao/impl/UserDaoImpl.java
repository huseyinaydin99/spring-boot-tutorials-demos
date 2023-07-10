package tr.com.huseyinaydin.dao.impl;

import tr.com.huseyinaydin.dao.UserDao;
import tr.com.huseyinaydin.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int insert(UserDto userDto) {
        String sql = "insert into tbl_user (id, username, password, create_at) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getCrateAt());
    }

    @Override
    public int update(UserDto userDto) {
        String sql = "update tbl_user set username=?, password=? where id=?";
        return jdbcTemplate.update(sql, userDto.getUsername(), userDto.getPassword(), userDto.getId());
    }

    @Override
    public UserDto select(int id) {
        String sql = "select * from tbl_user where id=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<UserDto>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto dto = new UserDto();
                dto.setId(rs.getInt("id"));
                dto.setUsername(rs.getString("username"));
                dto.setPassword(rs.getString("password"));
                dto.setCrateAt(rs.getDate("create_at"));
                return dto;
            }
        }, id);
    }

    @Override
    public int delete(int id) {
        String sql = "delete from tbl_user where id=?";
        return jdbcTemplate.update(sql, id);
    }
}