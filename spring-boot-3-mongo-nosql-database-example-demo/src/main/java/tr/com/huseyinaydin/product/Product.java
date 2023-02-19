package tr.com.huseyinaydin.product;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tr.com.huseyinaydin.category.Category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Document
@Data
@AllArgsConstructor
@Builder
public class Product {

  @Id
  private String id;
  private String name;
  private String description;
  private BigDecimal price;
  private double quantity;
  private double rating;

  private List<String> tags;

  @DBRef
  private Category category;

}
