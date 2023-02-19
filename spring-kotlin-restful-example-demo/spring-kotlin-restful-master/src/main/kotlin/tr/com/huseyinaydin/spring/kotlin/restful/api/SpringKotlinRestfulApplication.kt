package tr.com.huseyinaydin.spring.kotlin.restful.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 *
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
 *
 **/

@SpringBootApplication
open class SpringKotlinRestfulApplication
fun main(args: Array<String>) {
    SpringApplication.run(SpringKotlinRestfulApplication::class.java, *args)
}
