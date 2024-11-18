package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SpaceDto.CreateSpaceDto;
import com.example.demo.dto.SpaceDto.DeleteSpaceDto;
import com.example.demo.services.SpaceService;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    
    @Autowired
    SpaceService spaceService;

    @GetMapping
    public ResponseEntity<Object> getSpaces(
        @RequestParam(required = false) String query,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(spaceService.getSpace(query, page, size));
    }

    @PostMapping
    public ResponseEntity<Object> createSpace(CreateSpaceDto data) {
        var created = spaceService.createSpace(data);

        return created;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteSpace(DeleteSpaceDto data) {
        var deleted = spaceService.deleteSpace(data);

        return deleted;
    }
}
