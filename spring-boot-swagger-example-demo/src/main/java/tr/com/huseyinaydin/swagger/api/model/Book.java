package tr.com.huseyinaydin.swagger.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot Swagger.
* 
*/

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book implements Serializable {
	private static final long serialVersionUID = -1670893016517855654L;
	@Id
	@GeneratedValue
	@ApiModelProperty(value = "Otomatik üretilen ID değeri.")
	private int bookId;
	@ApiModelProperty(value = "Kitabın adı alanı.")
	private String bookName;
	@ApiModelProperty(value = "Kitabın fiyat alanı.")
	private double price;
}