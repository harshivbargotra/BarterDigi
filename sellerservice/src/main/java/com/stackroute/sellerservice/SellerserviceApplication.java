package com.stackroute.sellerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title = "Seller Microservice" , version="1.0"))
@EnableEurekaClient
public class SellerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerserviceApplication.class, args);
	}

}
