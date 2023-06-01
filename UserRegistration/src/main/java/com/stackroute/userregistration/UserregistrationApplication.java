package com.stackroute.userregistration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserregistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserregistrationApplication.class, args);
    }

}
