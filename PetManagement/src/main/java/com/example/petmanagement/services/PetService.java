package com.example.petmanagement.services;

import com.example.petmanagement.domain.*;
import com.example.petmanagement.dtos.PetEdit;
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

    public void savePet(PetRequest petRequest) {
        Pet pet = petMapper.petRequestToPetEntity(petRequest);
        petRepository.save(pet);
    }

    public PetEdit findPetById(Long petId){
        Pet pet = petRepository.findPetById(petId);
        return petMapper.petToPetEditDto(pet);
    }

    public List<PetResponse> findUserPets(Long userId) {
        Optional<UserAccount> user = userAccountRepository.findById(userId);
        if (user.isPresent()) {
            List<Pet> pets = petRepository.findUserPets(userId);

            return petMapper.petsToPetResponseDtos(pets);
        } else {
            throw new CustomNullPointerException("User not found!");
        }
    }

    public void editPet(Long petId, PetEdit petEdit) {
        Optional<Pet> optionalPet = petRepository.findById(petId);

        if (optionalPet.isPresent()) {
            Pet pet = petMapper.petEditToPetEntity(petEdit);
            petRepository.updatePet(petId, pet.getName(), pet.getCode(),
                    pet.getType(), pet.getFurColor(), pet.getCountryOfOrigin());
        } else {
            throw new CustomNullPointerException("Pet not found!");
        }
    }

    public List<PetColor> findAllPetColors() {
        return colorRepository.findAll();
    }

    public List<PetType> findAllPetTypes() {
        return typeRepository.findAll();
    }

    public List<PetCountry> findAllPetCountries() {
        return countryRepository.findAll();
    }
}
