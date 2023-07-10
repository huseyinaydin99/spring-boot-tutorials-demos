package tr.com.huseyinaydin.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Document(indexName = "blog")
@Getter @Setter
public class Article {
    @Id
    private Long id;

    @Field(type = FieldType.Text, name = "title")
    private String title;
}