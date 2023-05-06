package com.example.petmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Pet Management REST API Documentation",
                description = "Spring Boot REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Tregetter Ling",
                        email = "lingtregetter466@gmail.com"
                )
        )
)
public class PetManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetManagementApplication.class, args);
    }

}
