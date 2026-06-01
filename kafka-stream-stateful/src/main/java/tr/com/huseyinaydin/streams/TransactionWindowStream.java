package tr.com.huseyinaydin.streams;

import tr.com.huseyinaydin.events.Transaction;
import tr.com.huseyinaydin.serdes.TransactionSerde;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.time.Duration;

@Configuration // Spring konfigürasyon sınıfı olduğunu belirtir.
@EnableKafkaStreams // Kafka Streams özelliğini Spring Boot içinde aktif eder.
@Slf4j // Loglama işlemlerini kolaylaştırmak için Lombok logger ekler.
public class TransactionWindowStream {

    // Kaynak topic (transactions) üzerinden gelen veriler işlenir.
    // 10 saniyelik pencere içinde 3’ten fazla işlem yapılırsa fraud alert üretilir.
    // Sonuçlar user-txn-counts topic’ine yazılır.

    @Bean // Kafka Streams topology bean’ini Spring konteynerine ekler.
    public KStream<String, Transaction> windowedTransactionStream(StreamsBuilder builder) {

        KStream<String, Transaction> stream =
                builder.stream("transactions", Consumed.with(Serdes.String(), new TransactionSerde()));
        // "transactions" topic’inden gelen verileri Transaction stream olarak okur.

        // u1 - 5 işlem
        // u2 - 3 işlem

        stream.groupBy((key, tx) -> tx.userId(),
                        Grouped.with(Serdes.String(), new TransactionSerde())
                //Grouped.with(Serdes.String(), new TransactionSerde()), gruplama işlemi sırasında key’in String, value’nun ise Transaction formatında serileştirilip/deserileştirilmesini sağlayan yapılandırmadır.
                )
                // Verileri kullanıcı ID’sine göre gruplar.

                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(10)))
                // zaman damgası yoksa kayıtları Kafka’ya ulaştığı ana göre, varsa kayıt içindeki zaman damgasına göre 10 saniyelik zaman dilimlerine ayırarak işlem yapar.

                .count(Materialized.as("user-txn-count-window-store"))
                // Her kullanıcı için pencere bazlı işlem sayısını hesaplar ve store eder.

                .toStream()
                // Windowed sonucu tekrar stream yapısına çevirir.

                .peek((windowedKey, count) -> {
                    // Her pencere sonucu oluştuğunda loglama yapmak için çalışır.

                    String user = windowedKey.key();
                    // Windowed key içinden kullanıcı ID’sini alır.

                    log.info("🧾 Kullanıcı={} | Sayı={} | Pencere=[{} - {}]",
                            user,
                            count,
                            windowedKey.window().startTime(),
                            windowedKey.window().endTime());
                    // Kullanıcının belirli zaman aralığındaki işlem sayısını loglar.

                    if (count > 3) {
                        // Eğer 10 saniyede 3’ten fazla işlem varsa şüpheli kabul edilir.

                        log.warn("🚨 DOLANDIRICILIK UYARISI: Kullanıcı={} 10 saniye içinde {} işlem yaptı!", user, count);
                        // Şüpheli işlem durumunda uyarı logu basılır.
                    }
                })

                .to("user-txn-counts", Produced.with(
                        WindowedSerdes.timeWindowedSerdeFrom(String.class),
                        Serdes.Long()
                ));
        // zaman pencerelerine (windowed: belirli zaman aralıklarına bölünmüş veri grupları) ayrılmış kullanıcı işlem sayısı sonuçlarını Kafka’daki user-txn-counts topic’ine, key’i zaman penceresi + String kullanıcı bilgisi ve value’su Long olacak şekilde serileştirerek yazar.

        return stream; // Oluşturulan stream topolojisini Spring’e geri döndürür.
    }
}