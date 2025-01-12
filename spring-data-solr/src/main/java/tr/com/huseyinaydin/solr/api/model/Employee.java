package tr.com.huseyinaydin.solr.api.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
*
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "employees")
public class Employee {
	
	@Id
	@Field
	private int id;
	
	@Field
	private String name;
	
	@Field
	private String[] address;
}