package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PermissionEntity;
import com.example.demo.models.SpaceEntity;
import com.example.demo.models.UserEntity;

/*
 * Repositório para todas as querys da tabela "Permission". 
*/

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    List<PermissionEntity> findAllByPermissionId(Long id);

    Optional<PermissionEntity> findByUserIdAndSpaceId(UserEntity user, SpaceEntity space);
    void deleteByUserIdAndSpaceId(UserEntity user, SpaceEntity space);
}
