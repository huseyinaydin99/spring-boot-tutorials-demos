package tr.com.huseyinaydin.spring.data.jpa.postgresql.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Data
@NoArgsConstructor
public class KisiDto {
    private Long id;

    @NotNull
    private String adi;

    private String soyadi;

    private List<String> adresler;
}