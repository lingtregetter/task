package com.example.petmanagement.dtos;

import lombok.Data;

@Data
public class PetResponse {
    private Long id;
    private String name;
    private String code;
    private String type;
    private String furColor;
    private String countryOfOrigin;
}
