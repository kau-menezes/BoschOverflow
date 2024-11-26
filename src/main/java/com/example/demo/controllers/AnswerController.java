package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AnswerDto.CreateAnswerDto;
import com.example.demo.services.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    
    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<Object> postAnswer(@RequestBody CreateAnswerDto data) {
        System.err.println(data);
        var created = answerService.createAnswer(data);

        return created;
    }
}
