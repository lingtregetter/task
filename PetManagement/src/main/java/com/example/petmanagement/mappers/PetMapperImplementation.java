package com.example.petmanagement.mappers;

import com.example.petmanagement.domain.*;
import com.example.petmanagement.dtos.PetEdit;
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

    public PetMapperImplementation(TypeRepository typeRepository, ColorRepository colorRepository,
                                   CountryRepository countryRepository, UserAccountRepository userAccountRepository) {
        this.typeRepository = typeRepository;
        this.colorRepository = colorRepository;
        this.countryRepository = countryRepository;
        this.userAccountRepository = userAccountRepository;
    }

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

    @Override
    public List<PetResponse> petsToPetResponseDtos(List<Pet> pets) {
        List<PetResponse> petResponseList = new ArrayList<PetResponse>(pets.size());
        for (Pet pet : pets) {
            petResponseList.add(petToPetResponseDto(pet));
        }

        return petResponseList;
    }

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

    @Override
    public Pet petEditToPetEntity(PetEdit petEdit) {
        Pet pet = new Pet();
        pet.setName(petEdit.getName());
        pet.setCode(petEdit.getCode());
        pet.setType(typeById(petEdit.getTypeId()));
        pet.setFurColor(colorById(petEdit.getFurColorId()));
        pet.setCountryOfOrigin(countryById(petEdit.getCountryOfOriginId()));
        pet.setUserAccount(userById(petEdit.getUserId()));

        return pet;
    }

    @Override
    public PetEdit petToPetEditDto(Pet pet) {
        PetEdit petEdit = new PetEdit();
        petEdit.setUserId(pet.getUserAccount().getId());
        petEdit.setName(pet.getName());
        petEdit.setCode(pet.getCode());
        petEdit.setTypeId(pet.getType().getId());
        petEdit.setFurColorId(pet.getFurColor().getId());
        petEdit.setCountryOfOriginId(pet.getCountryOfOrigin().getId());

        return petEdit;
    }

    PetType typeById(Long id) {
        return typeRepository.findById(id).orElseThrow();
    }

    PetColor colorById(Long id) {
        return colorRepository.findById(id).orElseThrow();
    }

    PetCountry countryById(Long id) {
        return countryRepository.findById(id).orElseThrow();
    }

    UserAccount userById(Long id) {
        return userAccountRepository.findById(id).orElseThrow();
    }
}
