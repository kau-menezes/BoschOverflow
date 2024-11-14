package com.example.demo.dto.SpaceDto;

public record ChangeUserPermissionDto(
    String email,
    Long spaceId,
    String newPermission
) {}
