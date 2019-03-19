package com.example.copsbot;

import com.example.copsbot.user.InMemoryUniqueIdGenerator;
import com.example.copsbot.user.UniqueIdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CopsbotApplication {

    @Bean
    public UniqueIdGenerator<UUID> uniqueIdGenerator() {
        return new InMemoryUniqueIdGenerator();
    }

    public static void main(String[] args) {
        SpringApplication.run(CopsbotApplication.class, args);
    }

}
