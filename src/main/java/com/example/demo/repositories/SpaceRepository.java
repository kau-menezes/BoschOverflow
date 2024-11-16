package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.SpaceEntity;

/*
 * Repositório para a tabela de "Space". 
*/

@Repository
public interface SpaceRepository extends JpaRepository<SpaceEntity, Long> {
    Page<SpaceEntity> findByNameContaining(String query, PageRequest pageRequest);
}
