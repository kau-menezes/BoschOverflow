package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbQuestion") //  mudando o nome da tabela 
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    public Long getQuestionId() {
        return questionId;
    }

    @Column
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String questionText;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @ManyToOne
    @JoinColumn( name = "space_id")
    private SpaceEntity spaceId;

    public SpaceEntity getSpaceId() {
        return spaceId;
    }

    @ManyToOne
    @JoinColumn( name = "user_id")
    private UserEntity userId;

    public UserEntity getUserId() {
        return userId;
    }



}
