package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GetQuestion.GetQuestiondto;
import com.example.demo.dto.QuestionDto.CreateQuestionDto;
import com.example.demo.dto.QuestionDto.DeleteQuestionDto;
import com.example.demo.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @GetMapping("/{space}")
    public ResponseEntity<Object> getQuestion(
        @PathVariable("space") Long spaceId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {

        if (spaceId == null)
            return new ResponseEntity<>("Campo 'spaceId' vazio!", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(questionService.getAllQuestions(spaceId, page, size));
    }

    @GetMapping("/byId/{idQuestion}")
    public ResponseEntity<Object> getQuestionById(@PathVariable GetQuestiondto questiondto) { 
        var question = questionService.getQuestionById(questiondto);

        return question;
    }

    @PostMapping
    public ResponseEntity<Object> postQuestion(CreateQuestionDto data) {
        var created = questionService.createQuestion(data);

        return created;
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable DeleteQuestionDto data) {
        var deleted = questionService.deleteQuestion(data);

        return deleted;
    }
}
