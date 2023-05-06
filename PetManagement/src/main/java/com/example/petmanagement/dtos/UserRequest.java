package com.example.petmanagement.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull(message = "Username is required!")
    @NotEmpty(message = "Username field cannot be empty!")
    private String username;

    @NotNull(message = "Password is required!")
    @NotEmpty(message = "Password field cannot be empty!")
    private String password;
}
