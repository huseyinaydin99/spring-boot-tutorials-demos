package tr.com.huseyinaydin.jsondocs.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot JSONDocs.
* 
*/

@Entity
@Table
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiObject
public class Employee {
	@Id
	@GeneratedValue
	@ApiObjectField(name = "Çalışan Id'si yani tablodaki her bir satırı kimliklendirme.", description = "Çalışan varlık sınıfının Id alanının otomatik artması içindir.")
	private int id;
	@ApiObjectField(name = "Çalışan Adı", description = "Çalışanın Id alanıdır.")
	private String name;
	@ApiObjectField(name = "Departman", description = "Çalışanın çalıştığı deprartman alanıdır.")
	private String dept;
	@ApiObjectField(name = "Mayış", description = "Çalışanın maaşı alanıdır.")
	private double salary;
}