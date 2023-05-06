package com.example.petmanagement.controllers;

import com.example.petmanagement.domain.PetColor;
import com.example.petmanagement.domain.PetCountry;
import com.example.petmanagement.domain.PetType;
import com.example.petmanagement.dtos.PetEdit;
import com.example.petmanagement.dtos.PetRequest;
import com.example.petmanagement.dtos.PetResponse;
import com.example.petmanagement.services.PetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{userId}")
    public List<PetResponse> getUserPets(@PathVariable Long userId) {
        return petService.findUserPets(userId);
    }

    @GetMapping("/pet/{petId}")
    public PetEdit getPet(@PathVariable Long petId){
        return petService.findPetById(petId);
    }

    @PostMapping("")
    public void addPet(@RequestBody @Valid PetRequest pet) {
        petService.savePet(pet);
    }

    @PutMapping("/{id}")
    public void editPet(@PathVariable Long id, @RequestBody @Valid PetEdit pet) {
        petService.editPet(id, pet);
    }

    @GetMapping("/colors")
    public List<PetColor> getAllColors() {
        return petService.findAllPetColors();
    }

    @GetMapping("/countries")
    public List<PetCountry> getAllCountries() {
        return petService.findAllPetCountries();
    }

    @GetMapping("/types")
    public List<PetType> getAllTypes() {
        return petService.findAllPetTypes();
    }
}
