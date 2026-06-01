package tr.com.huseyinaydin.streams;

import tr.com.huseyinaydin.events.Transaction;
import tr.com.huseyinaydin.serdes.TransactionSerde;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
@Slf4j
public class FraudDetectionStream {

    // Bean oluşturma - Kafka Streams topolojisini tanımlar
    // -> topic’i oku
    // -> filtrele / işle
    // -> hedef topic’e yaz

    // Kullanılabilecek stream operasyonları:
    // filter(),        -> Koşula uyan kayıtları seçer
    // filterNot(),     -> Koşula uymayanları seçer
    // map(),           -> Key ve value üzerinde dönüşüm yapar
    // mapValues(),     -> Sadece value üzerinde dönüşüm yapar
    // flatMap(),       -> Bir kaydı birden fazla kayda böler
    // flatMapValues(), -> Value’yu birden fazla parçaya böler
    // branch(),        -> Akışı birden fazla dala ayırır
    // groupBy(),       -> Key’e göre gruplama yapar
    // aggregate(),     -> Gruplanmış veriler üzerinde birikimli hesaplama yapar
    // count()          -> Gruplanmış kayıt sayısını hesaplar

    @Bean
    public KStream<String, Transaction> fraudDetectStream(StreamsBuilder builder) {

        KStream<String, Transaction> stream =
                builder.stream("transactions", Consumed.with(Serdes.String(), new TransactionSerde()));
        // "transactions" topic’inden gelen verileri Kafka Streams akışına okur

        // stream
        //         .filter((key, tx) -> tx.amount() > 25000) // 25000 üzeri işlemleri filtreler
        //         .peek((key, tx) -> log.warn("⚠️ DOLANDIRICILIK UYARISI {}", tx)); // Şüpheli işlemleri loglar

        // stream
        //         .filterNot((key, tx) -> tx.amount() < 10000) // 10000 altı işlemleri elemez
        //         .peek((key, tx) -> log.warn("⚠️ normal işlem {}", tx)); // Kalan işlemleri loglar

        // stream.map((key, tx) ->
        //         KeyValue.pair(tx.userId(), "kullanıcı harcadı : " + tx.amount()) // key’i userId yapıp value’yu stringe çevirir
        // ).peek((key, value) ->
        //         log.info("Kullanıcı işlem özeti: Key: {}, Value: {}", key, value)); // dönüşen veriyi loglar

        // stream.mapValues(tx -> "İşlem: " + tx.amount() + " kullanıcı: " + tx.userId()) // sadece value’yu dönüştürür
        //         .peek((key, tx) ->
        //                 log.info("Sadece değer: Key: {}, Value: {}", key, tx)); // sonucu loglar

        // stream.flatMap((key, tx) -> {
        //     List<KeyValue<String, Item>> result = new ArrayList<>(); // tek transaction’dan çoklu item üretir
        //     for (Item item : tx.items()) {
        //         result.add(KeyValue.pair(tx.transactionId(), item));
        //     }
        //     return result;
        // }).peek((key, item) ->
        //         log.info("flatMap ---- Satın alınan ürün: Transaction ID: {}, Item: {}", key, item)); // her item’ı loglar

        // stream.flatMapValues(Transaction::items) // value içindeki item listesini açar
        //         .peek((key, item) ->
        //                 log.info("flatMapValues --- Sadece değer: Transaction ID: {}, Item: {}", key, item)); // item bazlı loglama

        // KStream<String, Transaction>[] branch = stream
        //         .branch(
        //                 (key, tx) -> tx.type().equalsIgnoreCase("debit"),  // debit işlemleri ayrı dala alır
        //                 (key, tx) -> tx.type().equalsIgnoreCase("credit") // credit işlemleri ayrı dala alır
        //         );

        // branch[0].peek((key, tx) ->
        //         log.info("Borc işlemi: Key: {}, Transaction: {}", key, tx)) // debit işlemleri loglar
        // .to("debit_transactions", Produced.with(Serdes.String(), new TransactionSerde())); // debit topic’ine yazar

        // branch[1].peek((key, tx) ->
        //         log.info("Alacak işlemi: Key: {}, Transaction: {}", key, tx)) // credit işlemleri loglar
        // .to("credit_transactions", Produced.with(Serdes.String(), new TransactionSerde())); // credit topic’ine yazar

        // stream
        //         .groupBy((key, tx) -> tx.location()) // lokasyona göre gruplar
        //         .count() // her lokasyon için işlem sayısını hesaplar
        //         .toStream()
        //         .peek((loc, count) ->
        //                 log.info("🌍 Konum {} için {} işlem var", loc, count)); // lokasyon bazlı sayım logu

        // stream.groupBy((key, tx) -> tx.userId()) // kullanıcıya göre gruplar
        //         .count(Materialized.as("user-txn-count-store")) // kullanıcı işlem sayısını store eder
        //         .toStream()
        //         .peek((userId, count) ->
        //                 log.info("👥 Kullanıcı {} toplam {} işlem yaptı", userId, count)); // kullanıcı bazlı sayım

        stream.groupBy((key, tx) -> tx.type()) // işlem tipine göre gruplar (debit/credit)
                .aggregate(
                        () -> 0.0, // başlangıç değeri
                        (type, tx, currentSum) -> currentSum + tx.amount(), // toplamı biriktirir
                        Materialized.with(Serdes.String(), Serdes.Double()) // state store tanımı
                )
                .toStream()
                .peek((type, total) ->
                        log.info("Kart tipi: {} | 💰 Toplam tutar: {}", type, total)); // tip bazlı toplamı loglar

        return stream;
    }
}