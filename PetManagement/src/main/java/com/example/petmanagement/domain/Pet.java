package com.example.petmanagement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private PetType type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private PetColor furColor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private PetCountry countryOfOrigin;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true)
    private UserAccount userAccount;
}
