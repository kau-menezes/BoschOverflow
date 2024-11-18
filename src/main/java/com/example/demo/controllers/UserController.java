package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto.CreateUserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDto data) {
        var created = userService.createUser(data);

        return created;
    }

    @GetMapping
    public ResponseEntity<Object> returnUser(
        @RequestParam(required = false) String query,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(userService.getUsers(query, page, size));
    }
}
