package com.springboot.ecommerce.springbootecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceApplication.class, args);
	}

}
