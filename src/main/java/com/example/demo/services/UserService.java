package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserDto.CreateUserDto;
import com.example.demo.models.UserEntity;

public interface UserService {
    ResponseEntity<UserEntity> createUser(CreateUserDto newUserData);
}
