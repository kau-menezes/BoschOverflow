package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.example.demo.dto.QuestionDto.CreateQuestionDto;
import com.example.demo.models.QuestionEntity;

/*
* Foram feitos DTO's específicos para cada uma das funções para melhor organização!
* Para entender melhor cada uma das funções, verifique os seus DTO's
*/ 

public interface QuestionService {
    ResponseEntity<Object> createQuestion(CreateQuestionDto newQuestionData);
    ResponseEntity<Object> deleteQuestion(Long questionData);
    List<QuestionEntity> getAllQuestions(Long spaceId, int page, int size);
    ResponseEntity<Object> getQuestionById(Long idQuestion);
}
