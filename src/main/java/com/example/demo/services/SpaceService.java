package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.SpaceDto.AddUserDto;
import com.example.demo.dto.SpaceDto.ChangeUserPermissionDto;
import com.example.demo.dto.SpaceDto.CreateSpaceDto;
import com.example.demo.dto.SpaceDto.DeleteSpaceDto;
import com.example.demo.models.SpaceEntity;

public interface SpaceService {
    ResponseEntity<SpaceEntity> createSpace(CreateSpaceDto newSpaceData);
    ResponseEntity<SpaceEntity> deleteSpace(DeleteSpaceDto spaceData);
    ResponseEntity<SpaceEntity> addUserToSpace(AddUserDto addUserData);
    ResponseEntity<SpaceEntity> changeUserPermission(ChangeUserPermissionDto userData);
}
