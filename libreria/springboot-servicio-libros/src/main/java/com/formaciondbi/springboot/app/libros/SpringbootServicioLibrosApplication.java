package com.formaciondbi.springboot.app.libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioLibrosApplication.class, args);
	}

}
