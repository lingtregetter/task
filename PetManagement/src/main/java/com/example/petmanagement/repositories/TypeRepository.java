package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<PetType, Long> {
}
