package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.events.Item;
import tr.com.huseyinaydin.events.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionNewController {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private static final String TOPIC = "transactions";
    private final Random random = new Random();

    @GetMapping("/produceTransactions")
    public String produceTransactions() throws InterruptedException {
        log.info("🚀 Rastgele işlemler yayımlanmaya başlanıyor...");

        List<String> users = Arrays.asList("U1", "U2", "U3");
        List<String> locations = Arrays.asList("Türkiye", "Hindistan", "ABD", "İngiltere", "Çin");
        List<String> types = Arrays.asList("borç", "alacak");

        for (int i = 0; i < 15; i++) {
            String user = users.get(random.nextInt(users.size()));
            Transaction tx = new Transaction(
                    UUID.randomUUID().toString(),
                    user,
                    1000 + random.nextInt(9000),
                    locations.get(random.nextInt(locations.size())),
                    types.get(random.nextInt(types.size())),
                    List.of(
                            new Item("I-" + random.nextInt(1000), "Ürün-" + random.nextInt(50), random.nextInt(5000), 1)
                    )
            );

            kafkaTemplate.send(TOPIC, user, tx);
            log.info("📤 {} kullanıcısı için işlem gönderildi: {}", user, tx);

            // Mesajların pencereler arasında yayılması için küçük bir gecikme
            TimeUnit.SECONDS.sleep(1);
        }

        log.info("✅ İşlem gönderimi tamamlandı!");
        return "İşlemler başarıyla yayımlandı!";
    }
}