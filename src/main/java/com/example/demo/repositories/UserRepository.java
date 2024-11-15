package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserEntity;

/*
 * Repositório para a tabela de "User". 
*/

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long>{
    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByEDV(String EDV);

    @Query("SELECT u FROM UserEntity u WHERE (:email IS NULL OR u.email = :email) OR (:EDV IS NULL OR u.EDV = :EDV)")
    Optional<UserEntity> findByEmailOrEDV(String email, String EDV);

    Page<UserEntity> findByNameContaining(String query, PageRequest pageRequest);
}
