package tr.com.huseyinaydin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class ProductService {

    private final ProductRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    public ProductService(ProductRepository repository, KafkaTemplate<String, String> kafkaTemplate,
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
        return "Veriler resetlendi";
    }

    @Transactional
    public void processProductIds(List<Long> productIds) {
        productIds.parallelStream()
                .forEach(this::fetchUpdateAndPublish);
    }

    private void fetchUpdateAndPublish(Long productId) {
        //ürün Id'ye göre çek
        Product product = repository.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Ürün sistemde bulunamadı!"));

        //indirim özelliklerini güncelle
        updateDiscountedPrice(product);

        //veritabanına kaydet
        repository.save(product);

        //kafka kuyruğuna yolla
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

    public List<Long> getProductIds(){
        return repository.findAll()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}