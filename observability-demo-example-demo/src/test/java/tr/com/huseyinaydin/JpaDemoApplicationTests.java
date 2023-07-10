package tr.com.huseyinaydin;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.huseyinaydin.controller.ProductController;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.repository.ProductRepository;
import tr.com.huseyinaydin.service.ProductService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

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
@RunWith(SpringRunner.class)
class JpaDemoApplicationTests {

    private static final String ENDPOINT_URL = "/products";

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.productController).build();
    }

    @Test
    public void addProductTest() throws Exception {
        Product demoProduct = new Product(101, "demo", 2000, "desc", "type");
        //when(productService.addProduct(any(Product.class))).thenReturn(demoProduct);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ENDPOINT_URL)
                        .content(asJsonString(demoProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void shouldReturnAllProductsFromDB() throws Exception {
        when(productService.getProducts()).thenReturn(Arrays.asList(
                new Product(101, "demo1", 2000, "desc1", "type1"),
                new Product(102, "demo2", 3000, "desc2", "type2")
        ));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(101));
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("duke"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("duke@spring.io"));
    }

    @Test
    public void getProductsById() throws Exception {
        when(productService.getProduct(108)).thenReturn(
                new Product(108, "demo1", 2000, "desc1", "type1"));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ENDPOINT_URL + "/{id}", 108)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(108));
    }


    @Test
    public void updateProductTest() throws Exception {
        Product product = new Product(101, "demo", 5000, "desc", "type");
        when(productService.updateProduct(anyInt(), any()))
                .thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders
                        .put(ENDPOINT_URL + "/{id}", 101)
                        .content(asJsonString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(5000));
    }


    @Test
    public void deleteProduct() throws Exception {
        when(productService.deleteProduct(101)).thenReturn("");
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/{id}", 101)
                )
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
