package tr.com.huseyinaydin.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.events.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public TransactionController(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @PostMapping
//    public String sendTransaction() throws Exception {
//
//        for (int i = 0; i < 50; i++) {
//
//            String transactionId = "txn-" + System.currentTimeMillis() + "-" + i;
//            double amount = 8000 + new Random().nextDouble() * (11000 - 8000);
//
//            Transaction txn = new Transaction(
//                    transactionId,
//                    "USER_" + i,
//                    amount, LocalDateTime.now().toString());
//
//            //String txnJson = mapper.writeValueAsString(txn);
//
//            kafkaTemplate.send("transactions", transactionId, txn);
//        }
//
//        return "✅ İşlem Kafka'ya gönderildi!";
//    }

    @PostMapping("/publish")
    public String publishTransaction() {
        List<Transaction> transactions = readTransactionsFromResource();

        for (Transaction txn : transactions) {
            kafkaTemplate.send("transactions", txn.transactionId(), txn);
        }
        return "✅ Kafka'ya " + transactions.size() + " adet işlem yayınlandı!";
    }

    private List<Transaction> readTransactionsFromResource() {
        // Classpath üzerindeki /transactions.json kaynağı için akışı aç
        try (InputStream is = getClass().getResourceAsStream("/transactions.json")) {
            return mapper.readValue(is, new TypeReference<List<Transaction>>() {
            });
        } catch (Exception e) {
            // Ayrıştırma hatalarını sar ve çalışma zamanı istisnası olarak tekrar fırlat
            throw new RuntimeException("transactions.json dosyası ayrıştırılamadı", e);
        }
    }
}