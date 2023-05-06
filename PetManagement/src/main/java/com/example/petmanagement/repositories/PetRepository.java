package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.Pet;
import com.example.petmanagement.domain.PetColor;
import com.example.petmanagement.domain.PetCountry;
import com.example.petmanagement.domain.PetType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select p from Pet p where p.userAccount.id = :userId")
    List<Pet> findUserPets(Long userId);

    @Query("select p from Pet p where p.id = :petId")
    Pet findPetById(Long petId);

    @Transactional
    @Modifying
    @Query("update Pet p set p.name = :name, p.code = :code, p.type = :type, p.furColor = :color, p.countryOfOrigin = :country where p.id = :petId")
    void updatePet(Long petId, String name, String code, PetType type, PetColor color, PetCountry country);
}
