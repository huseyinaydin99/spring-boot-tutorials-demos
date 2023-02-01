package tr.com.huseyinaydin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Entity
public class Employee {

	@Id
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}