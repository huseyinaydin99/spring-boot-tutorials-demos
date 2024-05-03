package tr.com.huseyinaydin.util;

import tr.com.huseyinaydin.dto.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

public class CsvReaderUtils {

    public static List<User> readDataFromCsv() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("users.csv").getInputStream()))) {
            CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }
}