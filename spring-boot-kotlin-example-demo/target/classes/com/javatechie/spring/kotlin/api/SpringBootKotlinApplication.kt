package com.javatechie.spring.kotlin.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SpringBootKotlinApplication

fun main(args: Array<String>) {
	SpringApplication.run(SpringBootKotlinApplication::class.java, *args)

	println("**********************Welcome to Java Techie****************************")
}
