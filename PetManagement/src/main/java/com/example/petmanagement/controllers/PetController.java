package com.example.petmanagement.controllers;

import com.example.petmanagement.domain.Pet;
import com.example.petmanagement.repositories.PetRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    private PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/api/pets")
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @GetMapping("/api/pets/{id}")
    public List<Pet> getUserPets(@PathVariable Long id) {
        return petRepository.findAllUserPets(id);
    }

    @PostMapping("/api/pets")
    public void addPet(@RequestBody @Valid Pet pet) {
        petRepository.savePet(pet);
    }
}
