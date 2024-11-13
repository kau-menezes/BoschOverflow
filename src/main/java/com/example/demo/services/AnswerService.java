package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.AnswerDto.CreateAnswerDto;
import com.example.demo.models.AnswerEntity;

/*
* Foram feitos DTO's específicos para cada uma das funções para melhor organização!
* Para entender melhor cada uma das funções, verifique os seus DTO's
*/ 

public interface AnswerService {
    
    ResponseEntity<AnswerEntity> createAnswer(CreateAnswerDto newAnswerDto);
}