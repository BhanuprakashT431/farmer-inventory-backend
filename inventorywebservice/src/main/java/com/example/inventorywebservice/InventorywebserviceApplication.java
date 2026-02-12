package com.example.inventorywebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class InventorywebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventorywebserviceApplication.class, args);
    }

    // This runs automatically when the server starts
    @Bean
    public CommandLineRunner run(UserRepository userRepository) {
        return args -> {
            // Create Admin/Farmer User (Added "FARMER" role)
            userRepository.save(new User("admin", "admin123", "FARMER"));

            // Create Buyer User (Added "BUYER" role)
            userRepository.save(new User("student", "password", "BUYER"));

            System.out.println("--- USERS CREATED: 'admin' (FARMER) and 'student' (BUYER) ---");

        };
    }
}