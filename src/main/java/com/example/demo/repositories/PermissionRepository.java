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
    List<PermissionEntity> findAllByPermissionId(Long permissionId);

    Optional<PermissionEntity> findByUserAndSpaceId(UserEntity User, SpaceEntity space);
    void deleteByUserAndSpaceId(UserEntity User, SpaceEntity space);

    // @Query("DELETE FROM PermissionEntity p WHERE p.space.id = :spaceId")
    void deleteAllBySpaceId(SpaceEntity spaceId);
}
