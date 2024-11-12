package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.AnswerEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    // List<AnswerEntity> findAnswersByQuestionId();
}