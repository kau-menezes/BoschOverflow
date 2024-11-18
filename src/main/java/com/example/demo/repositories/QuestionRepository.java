package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.QuestionEntity;
import com.example.demo.models.SpaceEntity;

/*
 * Repositório para a tabela de "Questions". 
*/

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Page<QuestionEntity> findBySpaceId(SpaceEntity spaceId, PageRequest pageRequest);
}

