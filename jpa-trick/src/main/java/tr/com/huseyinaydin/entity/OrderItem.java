package tr.com.huseyinaydin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

//Önemli: @Embeddable normalde tablo oluşturmaz, ancak @ElementCollection ile kullanıldığında koleksiyon verileri saklamak için ayrı bir tablo oluşturulur.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable //@Embeddable: Kendi başına bir tablo oluşturmayan; içindeki alanlar, kullanıldığı entity’nin tablosuna sütun olarak eklenen ve genelde adres, iletişim bilgisi gibi tekrar eden veri gruplarını tek bir sınıfta toplamak için kullanılan yapıdır.
public class OrderItem {
    private String productName;
    private int quantity;
}