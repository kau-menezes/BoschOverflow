package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.LoginDto.LoginDto;
import com.example.demo.dto.UserDto.CreateUserDto;

/*
* Foram feitos DTO's específicos para cada uma das funções para melhor organização!
* Para entender melhor cada uma das funções, verifique os seus DTO's
*/ 

public interface UserService {
    ResponseEntity<Object> createUser(CreateUserDto newUserData);
    ResponseEntity<Object> login(LoginDto loginData);    
    public boolean checkPassword(String password); // funções de teste
    public boolean checkEmail(String email); // funções de teste
    public boolean checkEdv(String edv); // funções de teste
}