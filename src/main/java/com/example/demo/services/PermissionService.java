package com.example.demo.services;

import org.springframework.http.ResponseEntity;

public interface PermissionService {

    ResponseEntity<Object> getPermission(String token);
}
