package com.example.petmanagement.controllers;

import com.example.petmanagement.domain.PetColor;
import com.example.petmanagement.domain.PetCountry;
import com.example.petmanagement.domain.PetType;
import com.example.petmanagement.dtos.PetRequest;
import com.example.petmanagement.dtos.PetResponse;
import com.example.petmanagement.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
    private PetService petService;

    /**
     * Constructor for class PetController
     * @param petService
     */
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Finds all user pets by user id from database
     * @param userId - user id to find user pets from database
     * @return List of PetResponse objects
     */
    @Operation(summary = "Finds all user pets by user id and returns list of PetResponses")
    @GetMapping("/{userId}")
    public List<PetResponse> getUserPets(@PathVariable Long userId) {
        return petService.findUserPets(userId);
    }

    /**
     * Finds pet by pet id from database
     * @param petId - pet id to find the right pet from database
     * @return PetEdit object
     */
    @Operation(summary = "Finds pet by pet id and returns PetRequest object")
    @GetMapping("/pet/{petId}")
    public PetRequest getPet(@PathVariable Long petId){
        return petService.findPetById(petId);
    }

    /**
     * Adds pet to database
     * @param pet - new pet object
     */
    @Operation(summary = "Add pet")
    @PostMapping("")
    public void addPet(@RequestBody @Valid PetRequest pet) {
        petService.savePet(pet);
    }

    /**
     * Edit's pet info in database
     * @param id - pet id to find the right pet from database
     * @param pet - updated pet object
     */
    @Operation(summary = "Edit's pet by pet id and new pet data")
    @PutMapping("/{id}")
    public void editPet(@PathVariable Long id, @RequestBody @Valid PetRequest pet) {
        petService.editPet(id, pet);
    }

    /**
     * Finds all pet colors from database
     * @return List of pet colors
     */
    @Operation(summary = "Finds all pet colors and returns list of pet colors")
    @GetMapping("/colors")
    public List<PetColor> getAllColors() {
        return petService.findAllPetColors();
    }

    /**
     * Finds all pet origin countries from database
     * @return List of pet origin countries
     */
    @Operation(summary = "Finds all pet origin countries and returns list of pet origin countries")
    @GetMapping("/countries")
    public List<PetCountry> getAllCountries() {
        return petService.findAllPetCountries();
    }

    /**
     * Finds all pet types from database
     * @return List of pet types
     */
    @Operation(summary = "Finds all pet types and returns list of pet types")
    @GetMapping("/types")
    public List<PetType> getAllTypes() {
        return petService.findAllPetTypes();
    }
}
