package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.client.ProductServiceClient;
import tr.com.huseyinaydin.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/product-client/products")
public class ProductClientController {

    @Autowired
    private ProductServiceClient serviceClient;


    @PostMapping
    public Product saveNewProduct(@RequestBody Product product) {
        return serviceClient.saveNewProduct(product);
    }


    @GetMapping
    public List<Product> getAllProducts(){
        return serviceClient.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return serviceClient.getProduct(id);
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id,@RequestBody Product product){
        return serviceClient.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        return serviceClient.deleteProduct(id);
    }
}