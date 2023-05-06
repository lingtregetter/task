package com.example.petmanagement.services;

import com.example.petmanagement.domain.*;
import com.example.petmanagement.dtos.PetRequest;
import com.example.petmanagement.dtos.PetResponse;
import com.example.petmanagement.exceptions.CustomNullPointerException;
import com.example.petmanagement.mappers.PetMapper;
import com.example.petmanagement.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private ColorRepository colorRepository;
    private TypeRepository typeRepository;
    private CountryRepository countryRepository;
    private PetRepository petRepository;
    private UserAccountRepository userAccountRepository;
    private PetMapper petMapper;

    /**
     * Constructor for class PetService
     * @param colorRepository
     * @param typeRepository
     * @param countryRepository
     * @param petRepository
     * @param userAccountRepository
     * @param petMapper
     */
    public PetService(ColorRepository colorRepository, TypeRepository typeRepository,
                      CountryRepository countryRepository, PetRepository petRepository,
                      UserAccountRepository userAccountRepository, PetMapper petMapper) {
        this.colorRepository = colorRepository;
        this.typeRepository = typeRepository;
        this.countryRepository = countryRepository;
        this.petRepository = petRepository;
        this.userAccountRepository = userAccountRepository;
        this.petMapper = petMapper;
    }

    /**
     * Method to save pet into database
     * Maps PetRequest dto into Pet object
     * @param petRequest - pet information to save pet into database
     */
    public void savePet(PetRequest petRequest) {
        Pet pet = petMapper.petRequestToPetEntity(petRequest);
        petRepository.save(pet);
    }

    /**
     * Method to find pet by id
     * Maps Pet object to PetRequest dto
     * @param petId - pet id to find pet from database
     * @return PetRequest object
     */
    public PetRequest findPetById(Long petId){
        Pet pet = petRepository.findPetById(petId);
        return petMapper.petToPetRequestDto(pet);
    }

    /**
     * Method to find all user pets by user id
     * Maps list of Pet objects to list of PetResponse dtos
     * @param userId - user id to find user pets from database
     * @return List of PetResponse objects
     */
    public List<PetResponse> findUserPets(Long userId) {
        Optional<UserAccount> user = userAccountRepository.findById(userId);
        if (user.isPresent()) {
            List<Pet> pets = petRepository.findUserPets(userId);

            return petMapper.petsToPetResponseDtos(pets);
        } else {
            throw new CustomNullPointerException("User not found!");
        }
    }

    /**
     * Method to edit pet data in database
     * Maps PetRequest dto to Pet object
     * @param petId - pet id to find the right pet from database
     * @param petRequest - updated pet object
     */
    public void editPet(Long petId, PetRequest petRequest) {
        Optional<Pet> optionalPet = petRepository.findById(petId);

        if (optionalPet.isPresent()) {
            Pet pet = petMapper.petRequestToPetEntity(petRequest);
            petRepository.updatePet(petId, pet.getName(), pet.getCode(),
                    pet.getType(), pet.getFurColor(), pet.getCountryOfOrigin());
        } else {
            throw new CustomNullPointerException("Pet not found!");
        }
    }

    /**
     * Method to find all pet colors from database
     * @return List of pet colors
     */
    public List<PetColor> findAllPetColors() {
        return colorRepository.findAll();
    }

    /**
     * Method to find all pet types from database
     * @return List of pet types
     */
    public List<PetType> findAllPetTypes() {
        return typeRepository.findAll();
    }

    /**
     * Method to find all pet origin countries from database
     * @return List of pet origin countries
     */
    public List<PetCountry> findAllPetCountries() {
        return countryRepository.findAll();
    }
}
