package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.AnswerEntity;

/*
 * Repositório para a tabela de "Answer". 
*/

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}