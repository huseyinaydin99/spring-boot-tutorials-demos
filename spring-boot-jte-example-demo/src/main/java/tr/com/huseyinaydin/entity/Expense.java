package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; // harcamanın benzersiz tanımlayıcısı
    public String description; // harcamanın açıklaması
    public double amount; // harcanan tutar
    public String date; // harcamanın yapıldığı tarih
    public String category; // harcama kategorisi (fatura, alış veriş, akar yakıt)
   // public String paymentMethod; // ödeme metodu (kredi kartı, nakit, banka kartı)
}