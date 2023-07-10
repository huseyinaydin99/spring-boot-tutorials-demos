package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.service.ProductService;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProduct(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        return service.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
