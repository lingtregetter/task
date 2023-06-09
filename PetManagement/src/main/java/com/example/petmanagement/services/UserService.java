package com.example.petmanagement.services;

import com.example.petmanagement.domain.UserAccount;
import com.example.petmanagement.dtos.UserResponse;
import com.example.petmanagement.exceptions.CustomNullPointerException;
import com.example.petmanagement.mappers.UserMapper;
import com.example.petmanagement.repositories.UserAccountRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private UserAccountRepository userAccountRepository;
    private UserMapper userMapper;

    /**
     * Constructor for class UserService
     * @param userAccountRepository
     * @param userMapper
     */
    public UserService(UserAccountRepository userAccountRepository, UserMapper userMapper) {
        this.userAccountRepository = userAccountRepository;
        this.userMapper = userMapper;
    }

    /**
     * Method to find user from database
     * Maps User object to UserResponse dto
     * @param username - username to find
     * @param password - password to find
     * @return UserResponse object
     */
    public UserResponse findUser(String username, String password) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findUserBy(username, password);
        if (optionalUserAccount.isPresent()) {
            UserAccount user = optionalUserAccount.get();
            return userMapper.userAccountToUserResponseDto(user);
        } else {
            throw new CustomNullPointerException("User not found!");
        }
    }
}
