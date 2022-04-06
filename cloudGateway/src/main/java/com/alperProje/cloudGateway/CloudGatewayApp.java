package com.alperProje.cloudGateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApp.class, args);
    }
}
