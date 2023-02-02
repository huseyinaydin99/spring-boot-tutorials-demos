package tr.com.huseyinaydin.cassandra.api.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@PrimaryKey
	private int id;
	private String name;
	private String address;
	private int age;

}
