package tr.com.huseyinaydin.spring.kotlin.api

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
open class SpringBootKotlinApplication

fun main(args: Array<String>) {
	SpringApplication.run(SpringBootKotlinApplication::class.java, *args)
	println("**********************Welcome to Java Techie****************************")
}