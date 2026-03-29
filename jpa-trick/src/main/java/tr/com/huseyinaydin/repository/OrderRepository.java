package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //Diyelim ki 5 sipariş var; 2 siparişin detaylarında ürün adı “silgi”, diğer 3 siparişin detaylarında ise “kalem” olsun. Kullanıcı “kalem” girdiğinde, sadece detaylarında “kalem” ürünü bulunan siparişler sonuç olarak gelir.
    //Bu sorgu, detay (items) tablosundaki ürün adı verilen değerle eşleşen en az bir ürüne sahip olan siparişleri bulup, sadece sipariş (Order) kayıtlarını döndürür.
    @Query("SELECT o FROM Order o JOIN o.items i WHERE i.productName = :name")
    List<Order> findByProductName(String name);
}