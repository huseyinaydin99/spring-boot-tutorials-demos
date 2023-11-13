package tr.com.huseyinaydin.fileupload.student;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCsvRepresentation {

    @CsvBindByName(column = "ad")
    private String fname;
    @CsvBindByName(column = "soyad")
    private String lname;
    @CsvBindByName(column = "yas")
    private int age;
}