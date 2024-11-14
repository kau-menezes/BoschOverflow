package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto.LoginDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginDto data) {

    }
}
