package tr.com.huseyinaydin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.huseyinaydin.controller.ProductController;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.respository.ProductRepository;
import tr.com.huseyinaydin.service.ProductService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@SpringBootTest
@AutoConfigureMockMvc
public class SpringDataJpaApplicationTests {

    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ProductController.class)
                .build();
    }

    @Test
    public void addProductTest() throws Exception {

        Product demoProduct = new Product(1, "demo", 1000, "demo product", "sample product");
        when(productRepository.save(any())).thenReturn(demoProduct);
        //URL -> /products -
        //HTTP METHOD : POST -
        //REQ & RESP : Product (Json String)

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(convertObjectAsString(demoProduct))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    public void getProductsShouldReturnAllProducts() throws Exception {

        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(1, "demo1", 1000, "demo product1", "sample product1")
                , new Product(2, "demo2", 2000, "demo product2", "sample product2")));

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
    }

    @Test
    public void getProductByIdTest() throws Exception {

        when(productRepository.findById(108)).thenReturn(Optional.of(new Product(108, "test", 200, "desc", "type")));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/{id}", 108)
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(108));
    }

    @Test
    public void updateProductTest() throws Exception {
        //input
        //id, product
        Product demoProduct = new Product(1, "demo", 1000, "demo product", "sample product");

        when(productRepository.findById(1)).thenReturn(Optional.of(demoProduct));
        when(productRepository.save(any())).thenReturn(new Product(1, "demo4", 4000, "demo product updated", "sample product 4"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", 1)
                        .content(convertObjectAsString(demoProduct))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("demo4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("demo product updated"));

    }

    @Test
    public void deleteProductByIdTest() throws Exception {

        Mockito.doNothing().when(productRepository).deleteById(anyInt());
        when(productRepository.count()).thenReturn(Long.valueOf(100));

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 12)
                ).andDo(print()).
                andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.").value(100));
    }

    private String convertObjectAsString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}