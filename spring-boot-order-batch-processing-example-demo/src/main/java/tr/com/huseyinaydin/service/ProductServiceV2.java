package tr.com.huseyinaydin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class ProductServiceV2 {

    private final ProductRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    private final ExecutorService executorService = Executors.newFixedThreadPool(6 );

    public ProductServiceV2(ProductRepository repository, KafkaTemplate<String, String> kafkaTemplate,
                            ObjectMapper objectMapper, @Value("${product.discount.update.topic}") String topicName) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.topicName = topicName;
    }

    public String resetRecords() {
        repository.findAll()
                .forEach(product -> {
                    product.setOfferApplied(false);
                    product.setPriceAfterDiscount(product.getPrice());
                    product.setDiscountPercentage(0);
                    repository.save(product);
                });
        return "Veriler resetlendi.";
    }

    public void executeProductIds(List<Long> productIds) {
        List<List<Long>> batches = splitIntoBatches(productIds, 50);

        List<CompletableFuture<Void>> futures = batches
                .stream()
                .map(batch -> CompletableFuture.runAsync(() -> processProductIds(batch), executorService))
                .collect(Collectors.toList());

        //tüm thread'ların tamamlanmasını bekliyorum
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private void processProductIds(List<Long> batch) {
        System.out.println("Toplu işlem " + batch + " ilgili thread " + Thread.currentThread().getName());
        batch.forEach(this::fetchUpdateAndPublish);
    }

    private void fetchUpdateAndPublish(Long productId) {
        //ürünü Id'ye göre çekiyorum.
        Product product = repository.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Ürün sistemde bulunamadı!"));

        //indirim özelliklerini güncelle
        updateDiscountedPrice(product);

        //veritabanına kaydet
        repository.save(product);

        //kafka kuyruğuna gönder.
        publishProductEvent(product);
    }

    private void updateDiscountedPrice(Product product) {
        double price = product.getPrice();
        int discountPercentage = (price >= 1000) ? 10 : (price > 500 ? 5 : 0);

        double priceAfterDiscount = price - (price * discountPercentage / 100);
        if (discountPercentage > 0) {
            product.setOfferApplied(true);
        }
        product.setDiscountPercentage(discountPercentage);
        product.setPriceAfterDiscount(priceAfterDiscount);
    }

    private void publishProductEvent(Product product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(topicName, productJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON'a çevrilirken hata oldu. ", e);
        }
    }

    public List<Long> getProductIds() {
        return repository.findAll()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    //batch List<Long> 1-50 , 2-100  ..
    private List<List<Long>> splitIntoBatches(List<Long> productIds, int batchSize) {
        int totalSize = productIds.size();
        int batchNums = (totalSize + batchSize - 1) / batchSize;
        List<List<Long>> batches = new ArrayList<>();

        for (int i = 0; i < batchNums; i++) {
            int start = i * batchSize;
            int end = Math.min(totalSize, (i + 1) * batchSize);
            batches.add(productIds.subList(start, end));
        }
        return batches;
    }
}