package com.accenture.loadgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoadGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadGeneratorApplication.class, args);
    }

}
