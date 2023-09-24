package tr.com.huseyinaydin;

import tr.com.huseyinaydin.config.DataSourceConfig;
import tr.com.huseyinaydin.di.OrderService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,AopAutoConfiguration.class})
@SpringBootApplication
public class InterviewQaApplication implements CommandLineRunner {

    @Value("${discount.offer.price}")
    private int discountPrice;

    @Value("${spring.profiles.active}")
    private String envArgs;

    @Autowired
    private Environment environment;

    @Autowired
    private DataSourceConfig config;



    @PostConstruct
    public void initLogic(){
        System.out.println("PostConstruct logic executed ...!");
        //connection pool logic
        //kafka producer/consumer instantiate
        //data shedding
        //external API call
    }


    public static void main(String[] args) {
        //ConfigurableApplicationContext context =
        System.out.println("SpringApplication run() method ....... executed");
        ConfigurableApplicationContext context = SpringApplication.run(InterviewQaApplication.class, args);
        OrderService service = context.getBean("orderService", OrderService.class);
       // service.test();

    }

    @Override
    public void run(String... args) throws Exception {
        //DB connection
        //populate some data to the db
        // pre-processing logic you want to perform
       // System.out.println("DISCOUNT PRICE :  "+environment.getProperty("discount.offer.price"));

        System.out.println("DISCOUNT PRICE :  "+ discountPrice);
        System.out.println(envArgs);
        System.out.println("Environment variable :  "+environment.getProperty("spring.profiles.active"));
        System.out.println("CONFIG VALUE : "+config);
        System.out.println("CommandLineRunner run() method ....... executed");
    }
}
