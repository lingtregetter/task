package com.example.petmanagement.mappers;

import com.example.petmanagement.domain.Pet;
import com.example.petmanagement.dtos.PetRequest;
import com.example.petmanagement.dtos.PetResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    PetResponse petToPetResponseDto(Pet pet);

    List<PetResponse> petsToPetResponseDtos(List<Pet> pets);

    Pet petRequestToPetEntity(PetRequest petRequest);

    PetRequest petToPetRequestDto(Pet pet);
}
