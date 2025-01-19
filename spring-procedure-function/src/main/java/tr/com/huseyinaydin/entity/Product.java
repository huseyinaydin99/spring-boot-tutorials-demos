package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
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

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "updateStockProcedure", //Spring tarafındaki referans adı. Bu adı, uygulamanın başka yerlerinde kullanarak bu saklı prosedüre ulaşabilirim.
                procedureName = "update_stock", //Database tarafındaki procedure adı. Spring, bu adı kullanarak veritabanındaki prosedüre erişir ve çalıştırır.
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "quantity", type = Integer.class)
                }
        )
})

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private int stockQuantity;
}