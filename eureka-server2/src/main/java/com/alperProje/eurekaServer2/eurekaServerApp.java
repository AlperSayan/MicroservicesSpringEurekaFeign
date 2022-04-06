package com.alperProje.eurekaServer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class eurekaServerApp {
    public static void main(String[] args) {
        SpringApplication.run(eurekaServerApp.class, args);
    }
}
