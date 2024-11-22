package com.example.demo.dto.LoginDto;

import com.example.demo.dto.UserDto.CreateUserDto;
import com.example.demo.models.UserEntity;

public record LoginResponseDto(
    String msg,
    userReturn user
) {}
