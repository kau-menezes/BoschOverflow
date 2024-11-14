package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.QuestionDto.CreateQuestionDto;
import com.example.demo.dto.QuestionDto.DeleteQuestionDto;
import com.example.demo.models.QuestionEntity;

/*
* Foram feitos DTO's específicos para cada uma das funções para melhor organização!
* Para entender melhor cada uma das funções, verifique os seus DTO's
*/ 

public interface QuestionService {
    ResponseEntity<Object> createQuestion(CreateQuestionDto newQuestionData);
    ResponseEntity<Object> deleteQuestion(DeleteQuestionDto questionData);
    // usar findAll para retornar todas as perguntas
    // usar finbyID retornaer pergunta específica
}
