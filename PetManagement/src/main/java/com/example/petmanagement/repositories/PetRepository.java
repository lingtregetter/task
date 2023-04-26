package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.Pet;
import com.example.petmanagement.domain.UserAccount;
import com.example.petmanagement.exceptions.CustomNullPointerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void savePet(Pet pet) {
        em.persist(pet);
    }

    public List<Pet> findAll() {
        String q = "select p.id, p.name, p.code, t.type, c.color, pc.country, u.username from Pet p " +
                "inner join PetColor c ON p.furColor.id = c.id " +
                "inner join PetType t ON p.type.id = t.id " +
                "inner join PetCountry pc ON p.countryOfOrigin.id = pc.id " +
                "inner join UserAccount u ON p.userAccount.id = u.id";

        return em.createQuery(q).getResultList();
    }

    public List<Pet> findAllUserPets(Long userId) {
        checkIfUserExists(userId);

        String q = "select p from Pet p " +
                "inner join PetColor c ON p.furColor.id = c.id " +
                "inner join PetType t ON p.type.id = t.id " +
                "inner join PetCountry pc ON p.countryOfOrigin.id = pc.id " +
                "inner join UserAccount u ON p.userAccount.id = u.id " +
                "where p.userAccount.id = :userId";

        return em.createQuery(q).setParameter("userId", userId).getResultList();
    }

    private void checkIfUserExists(Long userId) {
        UserAccount user = em.find(UserAccount.class, userId);

        if (user == null) {
            throw new CustomNullPointerException("User not found!");
        }
    }

    @Transactional
    public void editPet(Long petId, Pet pet) {
        Pet petFromDb = findPetById(petId);

        petFromDb.setName(pet.getName());
        petFromDb.setCode(pet.getCode());
        petFromDb.setType(pet.getType());
        petFromDb.setFurColor(pet.getFurColor());
        petFromDb.setCountryOfOrigin(pet.getCountryOfOrigin());

        em.merge(petFromDb);
    }

    public Pet findPetById(Long petId) {
        Pet pet = em.find(Pet.class, petId);

        if (pet == null) {
            throw new CustomNullPointerException("Pet not found!");
        }

        return pet;
    }
}
