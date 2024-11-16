package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SpaceDto.ChangeUserPermissionDto;
import com.example.demo.services.SpaceService;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    
    @Autowired
    SpaceService spaceService;

    @PostMapping
    public ResponseEntity<Object> changePermission(@RequestBody ChangeUserPermissionDto data) {
        var permission = spaceService.changeUserPermission(data);

        return permission;
    }
}
