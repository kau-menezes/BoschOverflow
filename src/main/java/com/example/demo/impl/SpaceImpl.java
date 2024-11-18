package com.example.demo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        if (newSpaceData.title() == null || newSpaceData.EDV() == null) {
            return new ResponseEntity<>("Campos vazios!", HttpStatus.BAD_REQUEST);
        }
    
        Optional<UserEntity> userOpt = repoUser.findByEDV(newSpaceData.EDV());
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
        UserEntity userEntity = userOpt.get();
    
        SpaceEntity spaceEntity = new SpaceEntity();
        spaceEntity.setTitle(newSpaceData.title());
    
        SpaceEntity savedSpace = repoSpace.save(spaceEntity);
    
        // Criar permissão de administrador (3) para o usuário
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setEDV(userEntity);
        permissionEntity.setSpaceId(savedSpace);
        permissionEntity.setPermission(3);
    
        repoPermission.save(permissionEntity);
    
        return new ResponseEntity<>("Espaço criado e permissão de administrador atribuída!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteSpace(DeleteSpaceDto spaceData) {
        if (spaceData.spaceId() == null)
            return new ResponseEntity<>("Campo vazio!", HttpStatus.BAD_REQUEST);

        var spaceOpt = repoSpace.findById(spaceData.spaceId());

        if (spaceOpt.isEmpty())
            return new ResponseEntity<>("Espaço não encontrado!", HttpStatus.NOT_FOUND);

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
        permissionEntity.setEDV(userEntity);

        repoPermission.save(permissionEntity);

        return new ResponseEntity<>("Usuário adicionado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> changeUserPermission(ChangeUserPermissionDto userData) {
        if (userData.email() == null || userData.spaceId() == null || userData.newPermission() == null)
            return new ResponseEntity<>("Campos vazios!", HttpStatus.BAD_REQUEST);

        var newPermission = Integer.parseInt(userData.newPermission());

        if (newPermission != 0 && newPermission != 1 && newPermission != 2)
            return new ResponseEntity<>("Valores errados!", HttpStatus.BAD_REQUEST);
        
        var user = repoUser.findByEmailOrEDV(userData.email(), null);
        var space = repoSpace.findById(userData.spaceId());

        if (user.isEmpty() || space.isEmpty()) {
            return new ResponseEntity<>("Usuário ou espaço não encontrado", HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = user.get();
        SpaceEntity spaceEntity = space.get();

        Optional<PermissionEntity> permissionOpt = repoPermission.findByEDVAndSpaceId(userEntity, spaceEntity);

        if (newPermission == 0) {
            if (permissionOpt.isPresent()) {
                repoPermission.deleteByEDVAndSpaceId(userEntity, spaceEntity);
                return new ResponseEntity<>("Permissao removida com sucesso!", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Permissao não encontrada", HttpStatus.NOT_FOUND);
            }
        }
        

        PermissionEntity permissionEntity; 
        if (permissionOpt.isEmpty()) {
            permissionEntity = new PermissionEntity();
            permissionEntity.setEDV(userEntity);
            permissionEntity.setSpaceId(spaceEntity);
            permissionEntity.setPermission(newPermission);

            repoPermission.save(permissionEntity);

            return new ResponseEntity<>("Permissão criada com sucesso!", HttpStatus.CREATED);
        }
        permissionEntity = permissionOpt.get();
        permissionEntity.setPermission(newPermission);

        repoPermission.save(permissionEntity);

        return new ResponseEntity<>("Permissão alterada com sucesso!", HttpStatus.OK);
    }

    @Override
    public List<SpaceEntity> getSpace(String query, int page, int size) {
        if (query == null || query.isEmpty()) {
            Page<SpaceEntity> spacePage = repoSpace.findAll(PageRequest.of(page, size));
            return spacePage.getContent();
        }

        Page<SpaceEntity> spacePage = repoSpace.findByTitleContaining(query, PageRequest.of(page, size));
        return spacePage.getContent();
    }
    
}
