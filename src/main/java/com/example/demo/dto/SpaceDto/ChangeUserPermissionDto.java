package com.example.demo.dto.SpaceDto;

public record ChangeUserPermissionDto(
    String EDV,
    Long spaceId,
    String newPermission
) {}
