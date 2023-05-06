package com.example.petmanagement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name field cannot be empty!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Code is required!")
    @NotEmpty(message = "Code field cannot be empty!")
    @Size(min = 12, max = 12)
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull(message = "Type is required!")
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private PetType type;

    @NotNull(message = "Color is required!")
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private PetColor furColor;

    @NotNull(message = "Country of origin is required!")
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private PetCountry countryOfOrigin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private UserAccount userAccount;
}
