package com.study.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"academy.devdojo.core.model"})
@EnableJpaRepositories({"academy.devdojo.core.repository"})
public class MicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesApplication.class, args);
    }

}
