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
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.SpaceService;

public class SpaceImpl implements SpaceService {

    @Autowired
    private SpaceRepository repoSpace;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private PermissionRepository repoPermission;

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
        if (addUserData.spaceId() == null || addUserData.email() == null || addUserData.permission() == null)
            return new ResponseEntity<>("Campo vazio!", HttpStatus.BAD_REQUEST);

        var space = repoSpace.findById(addUserData.spaceId());
        var user = repoUser.findByEmailOrEDV(addUserData.email(), null);

        if (space.isEmpty() || user.isEmpty())
            return new ResponseEntity<>("Campos vazios!", HttpStatus.NOT_FOUND);

        SpaceEntity spaceEntity = space.get();
        UserEntity userEntity = user.get();


        PermissionEntity permissionEntity = new PermissionEntity();

        permissionEntity.setPermission(addUserData.permission());
        permissionEntity.setSpaceId(spaceEntity);
        permissionEntity.setUserId(userEntity);

        repoPermission.save(permissionEntity);

        return new ResponseEntity<>("Usuário adicionado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> changeUserPermission(ChangeUserPermissionDto userData) {
        if (userData.email() == null || userData.spaceId() == null || userData.newPermission() == null)
            return new ResponseEntity<>("Campos vazios!", HttpStatus.BAD_REQUEST);
        
        
    }
    
}
