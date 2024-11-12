package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.QuestionDto.CreateQuestionDto;
import com.example.demo.dto.QuestionDto.DeleteQuestionDto;
import com.example.demo.models.QuestionEntity;

public interface QuestionService {
    ResponseEntity<QuestionEntity> createQuestion(CreateQuestionDto newQuestionData);
    ResponseEntity<QuestionEntity> deleteQuestion(DeleteQuestionDto questionData);
}
