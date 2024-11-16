package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.SpaceDto.AddUserDto;
import com.example.demo.dto.SpaceDto.ChangeUserPermissionDto;
import com.example.demo.dto.SpaceDto.CreateSpaceDto;
import com.example.demo.dto.SpaceDto.DeleteSpaceDto;
import com.example.demo.models.SpaceEntity;

/*
* Foram feitos DTO's específicos para cada uma das funções para melhor organização!
* Para entender melhor cada uma das funções, verifique os seus DTO's
*/ 

public interface SpaceService {
    ResponseEntity<Object> createSpace(CreateSpaceDto newSpaceData);
    ResponseEntity<Object> deleteSpace(DeleteSpaceDto spaceData);
    ResponseEntity<Object> addUserToSpace(AddUserDto addUserData);
    ResponseEntity<Object> changeUserPermission(ChangeUserPermissionDto userData);
    List<SpaceEntity> getSpace(String query, int page, int size);
}
