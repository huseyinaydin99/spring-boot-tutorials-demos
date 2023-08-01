package tr.com.huseyinaydin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "My Pet Object", description = "My Pet")
public class Pet {
    @ApiModelProperty(value = "Pet object unique id field")
    private int id;

    @ApiModelProperty(value = "Pet object name field")
    private String name;

    @ApiModelProperty(value = "Pet object date filed")
    private Date date;
}