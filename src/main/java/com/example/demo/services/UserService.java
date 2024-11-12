package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.LoginDto.LoginDto;
import com.example.demo.dto.LoginDto.LoginResponseDto;
import com.example.demo.dto.UserDto.CreateUserDto;
import com.example.demo.models.UserEntity;

/*
* Foram feitos DTO's específicos para cada uma das funções para melhor organização!
* Para entender melhor cada uma das funções, verifique os seus DTO's
*/ 

public interface UserService {
    ResponseEntity<UserEntity> createUser(CreateUserDto newUserData);
    ResponseEntity<LoginResponseDto> login(LoginDto loginData);
}
