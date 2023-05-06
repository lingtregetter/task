package com.example.petmanagement.mappers;

import com.example.petmanagement.domain.UserAccount;
import com.example.petmanagement.dtos.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImplementation implements UserMapper
{
    @Override
    public UserResponse userAccountToUserResponseDto(UserAccount userAccount) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userAccount.getId());
        userResponse.setUsername(userAccount.getUsername());

        return userResponse;
    }
}
