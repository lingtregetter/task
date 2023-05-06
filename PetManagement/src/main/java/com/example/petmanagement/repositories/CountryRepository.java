package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.PetCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<PetCountry, Long> {
}
