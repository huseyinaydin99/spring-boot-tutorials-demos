package tr.com.huseyinaydin.cache.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Hazlecast Cache.
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