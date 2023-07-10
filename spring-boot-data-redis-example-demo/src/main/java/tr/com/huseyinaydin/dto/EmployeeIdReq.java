package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
public class EmployeeIdReq implements Serializable {

    private static final long serialVersionUID = -8571454453894445935L;
    
    @JsonProperty("employees")
    private List<Long> id;
}