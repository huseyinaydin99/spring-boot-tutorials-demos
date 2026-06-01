package tr.com.huseyinaydin.events;

import java.util.List;

public record Transaction(
        String transactionId,
        String userId,
        double amount,
        String location,
        String type,
        List<Item> items
) {
}

//serializer //Deserializer
//serdes (Serializer/Deserializer), veriyi ağ üzerinden veya disk üzerinde taşınabilir hale getirmek için nesneleri byte dizisine dönüştüren (serialize) ve tekrar nesneye çeviren (deserialize) mekanizmadır.

//Serileştirici //Seri çözümleyici
//SerDes (Serileştirme / Seri Çözümleme)