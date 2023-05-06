package com.example.petmanagement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username is required!")
    @NotEmpty(message = "Username field cannot be empty!")
    private String username;

    @NotNull(message = "Password is required!")
    @NotEmpty(message = "Password field cannot be empty!")
    private String password;
}
