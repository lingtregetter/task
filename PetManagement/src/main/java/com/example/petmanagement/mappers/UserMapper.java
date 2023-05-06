package com.example.petmanagement.mappers;

import com.example.petmanagement.domain.UserAccount;
import com.example.petmanagement.dtos.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserResponse userAccountToUserResponseDto(UserAccount userAccount);
}
