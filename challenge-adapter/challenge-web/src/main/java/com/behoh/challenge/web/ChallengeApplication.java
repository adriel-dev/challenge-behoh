package com.behoh.challenge.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.behoh.challenge.persistence")
@EntityScan(basePackages = "com.behoh.challenge.persistence")
@SpringBootApplication(scanBasePackages = {"com.behoh.challenge.web", "com.behoh.challenge.persistence"})
public class ChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }
}
