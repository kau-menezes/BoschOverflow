package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbPermission") //  mudando o nome da tabela 
public class PermissionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @OneToOne
    // @PrimaryKeyJoinColumn
    @JoinColumn(name = "space_id")
    private SpaceEntity spaceId;

    public SpaceEntity getSpaceId() {
        return spaceId;
    }

    @OneToOne
    // @PrimaryKeyJoinColumn
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    public UserEntity getUserId() {
        return userId;
    }

    @Column
    private Integer permission;  // LER A DOCUMENTAÇÃO PARA ENTENDER O QUE É CADA COISA AQUI

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

}
