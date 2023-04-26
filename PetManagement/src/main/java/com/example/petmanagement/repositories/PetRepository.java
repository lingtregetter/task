package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.Pet;
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
        String q = "select p.id, p.name, p.code, t.type, c.color, pc.country, u.username from Pet p " +
                "inner join PetColor c ON p.furColor.id = c.id " +
                "inner join PetType t ON p.type.id = t.id " +
                "inner join PetCountry pc ON p.countryOfOrigin.id = pc.id " +
                "inner join UserAccount u ON p.userAccount.id = u.id " +
                "where p.userAccount.id = :userId";

        return em.createQuery(q).setParameter("userId", userId).getResultList();
    }
}
