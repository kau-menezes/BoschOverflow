package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * Essa entidade é a entidade de conexão de um espaço com um usuário.
 * Por ela podemos também mudar a permissão de um usuário dentro de um espaço. 
  
 * Os níveis de permissão podem ser:
 *      0: Usuário leitor
 *      1: Usuário que pode responder e perguntar.
 *      2: Usuário administrador do espaço, pode adicionar outros usuários, transformar em adm e apagar o espaço e as perguntas.
*/
@Entity
@Table(name = "tbPermission") // Mudando o nome da tabela
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    public Long getPermissionId() {
        return permissionId;
    }

    @ManyToOne
    @JoinColumn(name = "space_id") // Chave estrangeira para a tabela tbSpace
    private SpaceEntity spaceId;

    @ManyToOne
    @JoinColumn(name ="EDV") 
    private UserEntity EDV;

    public SpaceEntity getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(SpaceEntity spaceId) {
        this.spaceId = spaceId;
    }

    // Usar userId em vez de EDV para a chave estrangeira

    public UserEntity getEDV() {
        return EDV;
    }

    public void setEDV(UserEntity EDV) {
        this.EDV = EDV;
    }

    @Column
    private Integer permission;

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}


