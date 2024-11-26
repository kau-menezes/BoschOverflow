package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.AnswerDto.CreateAnswerDto;
import com.example.demo.models.AnswerEntity;
import com.example.demo.models.QuestionEntity;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.services.AnswerService;

public class AnswerImpl implements AnswerService {

    @Autowired
    private QuestionRepository repoQuestion;

    @Autowired
    private AnswerRepository repoAnswer;

    @Override
    public ResponseEntity<Object> createAnswer(CreateAnswerDto newAnswerDto) {

        AnswerEntity answer = new AnswerEntity();

        var text = newAnswerDto.text();

        if(text == null)
            return new ResponseEntity<>("Campos vazios", HttpStatus.BAD_REQUEST);

        var questionId = newAnswerDto.questionId();

        var id = repoQuestion.findById(questionId);
        QuestionEntity idQuestion = id.get();

        answer.setAnswerText(text);
        answer.setQuestionId(idQuestion);
        answer.setEDV(newAnswerDto.EDV());

        repoAnswer.save(answer);
        
        return new ResponseEntity<>("Criado com sucesso!", HttpStatus.CREATED);
    }
    
}
