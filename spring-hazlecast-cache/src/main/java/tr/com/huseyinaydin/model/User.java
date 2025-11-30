package tr.com.huseyinaydin.model;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

@Getter
@Setter
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -6912201477750422475L;

	@Id
	private int id;

    private String name;
	private String address;
}