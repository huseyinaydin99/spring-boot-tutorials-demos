package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot Virtual Thread vs Platform Thread.
 *
 */

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //Sanal akış ile platform akışı arasında performans testi için yazıldı.
    @GetMapping
    public List<Product> getProducts() throws InterruptedException {
        Thread.sleep(1000); // 1000 milisaniye yani 1 saniye uyutuyor metodun thread'ını.
        log.info("Thread(bir process'in içindeki alt akışlar(işlemler) Process ağır siklettir Thread hafif siklet.) bilgisi: {} ", Thread.currentThread());
        return productRepository.findAll();
    }
}