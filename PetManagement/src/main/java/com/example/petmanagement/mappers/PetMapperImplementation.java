package com.example.petmanagement.mappers;

import com.example.petmanagement.domain.*;
import com.example.petmanagement.dtos.PetRequest;
import com.example.petmanagement.dtos.PetResponse;
import com.example.petmanagement.repositories.ColorRepository;
import com.example.petmanagement.repositories.CountryRepository;
import com.example.petmanagement.repositories.TypeRepository;
import com.example.petmanagement.repositories.UserAccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetMapperImplementation implements PetMapper {
    private TypeRepository typeRepository;
    private ColorRepository colorRepository;
    private CountryRepository countryRepository;
    private UserAccountRepository userAccountRepository;

    /**
     * Constructor for class PetMapperImplementation
     * @param typeRepository
     * @param colorRepository
     * @param countryRepository
     * @param userAccountRepository
     */
    public PetMapperImplementation(TypeRepository typeRepository, ColorRepository colorRepository,
                                   CountryRepository countryRepository, UserAccountRepository userAccountRepository) {
        this.typeRepository = typeRepository;
        this.colorRepository = colorRepository;
        this.countryRepository = countryRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Method to map pet object to pet response dto
     * @param pet - pet to map
     * @return PetResponse object
     */
    @Override
    public PetResponse petToPetResponseDto(Pet pet) {
        PetResponse petResponse = new PetResponse();
        petResponse.setId(pet.getId());
        petResponse.setName(pet.getName());
        petResponse.setCode(pet.getCode());
        petResponse.setType(pet.getType().getType());
        petResponse.setFurColor(pet.getFurColor().getColor());
        petResponse.setCountryOfOrigin(pet.getCountryOfOrigin().getCountry());

        return petResponse;
    }

    /**
     * Method to map list of pet objects to list of pet response dtos
     * @param pets - List of pet objects
     * @return List of PetResponse objects
     */
    @Override
    public List<PetResponse> petsToPetResponseDtos(List<Pet> pets) {
        List<PetResponse> petResponseList = new ArrayList<PetResponse>(pets.size());
        for (Pet pet : pets) {
            petResponseList.add(petToPetResponseDto(pet));
        }

        return petResponseList;
    }

    /**
     * Method to map pet request dto to pet object
     * @param petRequest - petRequest to map
     * @return - Pet object
     */
    @Override
    public Pet petRequestToPetEntity(PetRequest petRequest) {
        Pet pet = new Pet();
        pet.setName(petRequest.getName());
        pet.setCode(petRequest.getCode());
        pet.setType(typeById(petRequest.getTypeId()));
        pet.setFurColor(colorById(petRequest.getFurColorId()));
        pet.setCountryOfOrigin(countryById(petRequest.getCountryOfOriginId()));
        pet.setUserAccount(userById(petRequest.getUserId()));

        return pet;
    }

    /**
     * Method to map pet object to pet request dto
     * @param pet - pet to map
     * @return PetRequest object
     */
    @Override
    public PetRequest petToPetRequestDto(Pet pet) {
        PetRequest petRequest = new PetRequest();
        petRequest.setUserId(pet.getUserAccount().getId());
        petRequest.setName(pet.getName());
        petRequest.setCode(pet.getCode());
        petRequest.setTypeId(pet.getType().getId());
        petRequest.setFurColorId(pet.getFurColor().getId());
        petRequest.setCountryOfOriginId(pet.getCountryOfOrigin().getId());

        return petRequest;
    }

    private PetType typeById(Long id) {
        return typeRepository.findById(id).orElseThrow();
    }

    private PetColor colorById(Long id) {
        return colorRepository.findById(id).orElseThrow();
    }

    private PetCountry countryById(Long id) {
        return countryRepository.findById(id).orElseThrow();
    }

    private UserAccount userById(Long id) {
        return userAccountRepository.findById(id).orElseThrow();
    }
}
