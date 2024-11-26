package com.example.demo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.GetQuestion.GetQuestiondto;
import com.example.demo.dto.QuestionDto.CreateQuestionDto;
import com.example.demo.dto.QuestionDto.DeleteQuestionDto;
import com.example.demo.models.QuestionEntity;
import com.example.demo.models.SpaceEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.QuestionService;

import jakarta.persistence.EntityNotFoundException;

public class QuestionImpl implements QuestionService{

    @Autowired
    private QuestionRepository repoQuestion;

    @Autowired
    private SpaceRepository repoSpace;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> createQuestion(CreateQuestionDto newQuestionData) {
        // Verificar se o usuário e o espaço existem
        Optional<UserEntity> userOpt = userRepository.findById(newQuestionData.userId());
        Optional<SpaceEntity> spaceOpt = repoSpace.findById(newQuestionData.spaceId());
    
        if (userOpt.isEmpty()) {
            // Retornar 404 se o usuário não for encontrado
            return new ResponseEntity<>("Usuário não encontrado!", HttpStatus.NOT_FOUND);
        }
    
        if (spaceOpt.isEmpty()) {
            // Retornar 404 se o espaço não for encontrado
            return new ResponseEntity<>("Espaço não encontrado!", HttpStatus.NOT_FOUND);
        }
    
        // Obter as entidades de usuário e espaço
        UserEntity user = userOpt.get();
        SpaceEntity space = spaceOpt.get();
    
        // Criando a questão
        QuestionEntity question = new QuestionEntity();
        question.setQuestionTitle(newQuestionData.questionTitle());
        question.setQuestionText(newQuestionData.questionText());
        question.setEDV(user);  // Associar o User
        question.setSpaceId(space); // Associar o Space
    
        try {
            // Salvar a questão no banco
            repoQuestion.save(question);
        } catch (Exception e) {
            // Caso ocorra algum erro durante a persistência
            return new ResponseEntity<>("Erro ao salvar a questão: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        // Retornar uma resposta de sucesso
        return new ResponseEntity<>("Questão criada com sucesso!", HttpStatus.CREATED);
    }
    

    @Override
    public ResponseEntity<Object> deleteQuestion(Long idQuestion) {
        if(repoQuestion.findById(idQuestion).isEmpty())
            return new ResponseEntity<>("Questao não encontrada no banco", HttpStatus.BAD_REQUEST);

        repoQuestion.deleteById(idQuestion);
        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
    }
    @Override
    public List<QuestionEntity> getAllQuestions(Long spaceId, int page, int size) {

        Optional<SpaceEntity> spaceOpt = repoSpace.findById(spaceId);
        
        if (spaceOpt.isEmpty()) {
            throw new EntityNotFoundException("Espaço não encontrado!");
        }
        
        SpaceEntity space = spaceOpt.get();
        
        // Usando a SpaceEntity para filtrar as questões
        Page<QuestionEntity> questionPage = repoQuestion.findBySpaceId(space, PageRequest.of(page, size));
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
