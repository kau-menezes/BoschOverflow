package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbSpace") //  mudando o nome da tabela 
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
