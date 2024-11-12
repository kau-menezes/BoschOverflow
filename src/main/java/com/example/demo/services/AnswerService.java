package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.AnswerEntity;

public interface AnswerService {
    ResponseEntity<AnswerEntity> createAnswer(String answerText);
}
