package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.dto.Token;
import com.example.demo.models.PermissionEntity;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.services.JWTService;
import com.example.demo.services.PermissionService;

public class PermissionImpl implements PermissionService {

    // Retorna todas as permissões do usuário, podemos usar essa impl sempre que precisarmos da permissão

    @Autowired
    JWTService jwtService;

    @Autowired
    private PermissionRepository repo;

    @Override
    public ResponseEntity<Object> getPermission(@RequestHeader("Authorization") String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não enviado ou no formato incorreto", HttpStatus.UNAUTHORIZED);
        }

        String token = authorization.substring(7);  // Remover "Bearer " do token

        // Valida o token
        Token userToken = (Token) jwtService.validate(token);
        if (userToken == null) {
            return new ResponseEntity<>("Token inválido ou expirado", HttpStatus.UNAUTHORIZED);
        }

        List<PermissionEntity> permissions = repo.findAllById(userToken.getId());

        return new ResponseEntity<>(permissions, HttpStatus.ACCEPTED);

    }
    
}
