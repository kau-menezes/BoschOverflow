package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Essa entidade é a entidade de um espaço.
 * Note que não é feita nenhuma conexão com usuário aqui, logo o usuário deve ser conectado pela tabela "Permission" como permissão nível 2. 
*/

@Entity
@Table(name = "tbSpace") // Mudando o nome da tabela 
public class SpaceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    public Long getSpaceId() {
        return spaceId;
    }

    @Column
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // @ManyToMany


}
