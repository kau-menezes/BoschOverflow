package com.example.demo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.GetQuestion.GetQuestiondto;
import com.example.demo.dto.QuestionDto.CreateQuestionDto;
import com.example.demo.dto.QuestionDto.DeleteQuestionDto;
import com.example.demo.models.QuestionEntity;
import com.example.demo.models.SpaceEntity;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.QuestionService;

public class QuestionImpl implements QuestionService{

    @Autowired
    private QuestionRepository repoQuestion;

    @Autowired
    private SpaceRepository repoSpace;

    @Override
    public ResponseEntity<Object> createQuestion(CreateQuestionDto newQuestionData) {

        var title = newQuestionData.title();
        var text = newQuestionData.text();
        var spaceId = newQuestionData.spaceId();

        if(title == null || text == null || spaceId == null)
            return new ResponseEntity<>("Campos vazios",HttpStatus.BAD_REQUEST);

        QuestionEntity question = new QuestionEntity();
        question.setQuestionText(text);
        question.setQuestionTitle(title);
        
        var id = repoSpace.findById(spaceId);
        SpaceEntity idSpace = id.get();

        question.setSpaceId(idSpace);
        
        repoQuestion.save(question);

        return new ResponseEntity<>("Criado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteQuestion(DeleteQuestionDto questionData) {
        Long idQuestion = questionData.questionId();
        if(repoQuestion.findById(idQuestion).isEmpty())
            return new ResponseEntity<>("Questao não encontrada no banco", HttpStatus.BAD_REQUEST);

        repoQuestion.deleteById(idQuestion);
        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
    }

    @Override
    public List<QuestionEntity> getAllQuestions(Long spaceId, int page, int size) {
        Page<QuestionEntity> questionPage = repoQuestion.findBySpaceIdContaining(spaceId, PageRequest.of(page, size));
        return questionPage.getContent();
    }

    @Override
    public ResponseEntity<Object> getQuestionById(GetQuestiondto questionData) {
        if (questionData.id() == null) {
            return new ResponseEntity<>("O ID da questão não pode ser nulo!", HttpStatus.BAD_REQUEST);
        }
    
        Optional<QuestionEntity> questionOpt = repoQuestion.findById(questionData.id());
    
        if (questionOpt.isEmpty()) {
            return new ResponseEntity<>("Questão não encontrada!", HttpStatus.NOT_FOUND);
        }
    
        return new ResponseEntity<>(questionOpt.get(), HttpStatus.OK);
    }
    
}
