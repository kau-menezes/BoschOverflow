package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.SpaceDto.AddUserDto;
import com.example.demo.dto.SpaceDto.ChangeUserPermissionDto;
import com.example.demo.dto.SpaceDto.CreateSpaceDto;
import com.example.demo.dto.SpaceDto.DeleteSpaceDto;
import com.example.demo.models.PermissionEntity;
import com.example.demo.models.SpaceEntity;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.SpaceService;

public class SpaceImpl implements SpaceService {

    @Autowired
    private SpaceRepository repoSpace;

    @Autowired // TA ERRADO!
    private PermissionEntity repoPermission;

    @Override
    public ResponseEntity<Object> createSpace(CreateSpaceDto newSpaceData) {
        if(newSpaceData.title() == null) 
            return new ResponseEntity<>("Campo vazio", HttpStatus.BAD_REQUEST);

        SpaceEntity spaceEntity = new SpaceEntity();
        spaceEntity.setTitle(newSpaceData.title());

        repoSpace.save(spaceEntity);
        return new ResponseEntity<>("Espaço criado", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteSpace(DeleteSpaceDto spaceData) {
        if (spaceData.spaceId() == null)
            return new ResponseEntity<>("Campo vazio!", HttpStatus.BAD_REQUEST);

        repoSpace.deleteById(spaceData.spaceId());

        return new ResponseEntity<>("Espaço excluído", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> addUserToSpace(AddUserDto addUserData) {
        if (addUserData.spaceId() == null || addUserData.email() == null)
            return new ResponseEntity<>("Campo vazio!", HttpStatus.BAD_REQUEST);

        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermission(addUserData.permission());
        permissionEntity.setSpaceId(addUserData.spaceId());
        permissionEntity.set
    }

    @Override
    public ResponseEntity<Object> changeUserPermission(ChangeUserPermissionDto userData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeUserPermission'");
    }
    
}
