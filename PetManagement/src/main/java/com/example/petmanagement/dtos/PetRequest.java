package com.example.petmanagement.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetRequest {
    @NotNull(message = "User is required!")
    private Long userId;

    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name field cannot be empty!")
    private String name;

    @NotNull(message = "Code is required!")
    @NotEmpty(message = "Code field cannot be empty!")
    @Size(min = 12, max = 12)
    private String code;

    @NotNull(message = "Type is required!")
    private Long typeId;

    @NotNull(message = "Color is required!")
    private Long furColorId;

    @NotNull(message = "Country of origin is required!")
    private Long countryOfOriginId;
}
