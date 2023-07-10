package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
	private static final long serialVersionUID = 4112198546343192807L;

	private Long id;
	
	@JsonProperty("employee_id")
	private String employeeId;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	private String gender;
	
	@JsonProperty("work_dept")
	private String workDept;
}