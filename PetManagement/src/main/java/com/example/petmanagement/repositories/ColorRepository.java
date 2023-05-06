package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.PetColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<PetColor,Long> {
}
