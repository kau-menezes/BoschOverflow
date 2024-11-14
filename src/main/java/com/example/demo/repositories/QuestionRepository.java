package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.QuestionEntity;

/*
 * Repositório para a tabela de "Questions". 
*/

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    
}
