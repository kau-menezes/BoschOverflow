package com.example.demo.repositories;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> d2b2b53cd242946cbcc38778360241e2fb12ebe9
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PermissionEntity;

/*
 * Repositório para todas as querys da tabela "Permission". 
*/

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
<<<<<<< HEAD
    
=======

    List<PermissionEntity> findAllById(Long id);
>>>>>>> d2b2b53cd242946cbcc38778360241e2fb12ebe9
}
