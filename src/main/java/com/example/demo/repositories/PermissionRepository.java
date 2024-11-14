package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PermissionEntity;

/*
 * Repositório para todas as querys da tabela "Permission". 
*/

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    
}
