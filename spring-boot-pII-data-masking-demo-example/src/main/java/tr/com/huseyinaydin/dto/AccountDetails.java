package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.encrypt.MaskData;

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
@Builder
public class AccountDetails {

    private String accountHolderName;
    @MaskData
    private String accountNumber;
    private String accountType;
}
