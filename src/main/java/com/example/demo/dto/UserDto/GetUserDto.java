package com.example.demo.dto.UserDto;

public record GetUserDto(
    String query,
    Integer page,
    Integer size
) {}
