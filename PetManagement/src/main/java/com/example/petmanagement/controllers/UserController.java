package com.example.petmanagement.controllers;

import com.example.petmanagement.dtos.UserRequest;
import com.example.petmanagement.dtos.UserResponse;
import com.example.petmanagement.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid UserRequest userRequest){
        return userService.findUser(userRequest.getUsername(), userRequest.getPassword());
    }
}