package com.example.demo.dto.UserDto;

public record CreateUserDto (
    String username,
    String email,
    String password,
    String EDV
) {}
