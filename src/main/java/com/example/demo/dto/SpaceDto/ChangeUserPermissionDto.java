package com.example.demo.dto.SpaceDto;

public record ChangeUserPermissionDto(
    String email,
    String newPermission
) {}
