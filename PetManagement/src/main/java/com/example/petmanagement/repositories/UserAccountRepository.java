package com.example.petmanagement.repositories;

import com.example.petmanagement.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("select u from UserAccount u where u.username = :username and u.password = :password")
    Optional<UserAccount> findUserBy(String username, String password);

}
