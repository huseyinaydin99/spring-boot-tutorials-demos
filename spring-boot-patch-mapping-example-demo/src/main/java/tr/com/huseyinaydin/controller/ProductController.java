package tr.com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.service.ProductService;

import java.util.List;
import java.util.Map;

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
        return service.saveProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }


    @PutMapping("/{id}")//sadece belli alanları postman ile gönderirsek diğer alanlar null olarak güncellenir.
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
       return service.updateProduct(id, productRequest);
    }

    @PatchMapping("/{id}")//sadece belli olanları postman ile gönderirsek diğer alanlar null olmaz. var olna değerler olduğu gibi kalır. bu noktada sadece belirli kolonlar güncellenmektedir.
    public Product updateProductFields(@PathVariable int id,@RequestBody Map<String, Object> fields){
        return service.updateProductByFields(id,fields);
    }

    @DeleteMapping("/{id}")
    public long deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}