package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbAnswer") // Mudando o nome da tabela 
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asnwerId;

    public Long getAnswerId() {
        return asnwerId;
    }

    @Column
    public String answerText;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /*
     * Conectando a Answer com a sua devida Question
    */

    @ManyToOne
    @JoinColumn( name = "space_id")
    private QuestionEntity questionId;

    public QuestionEntity getQuestionId() {
        return questionId;
    }

    /*
     * Conectando a Answer com o usuário que a criou
    */

    @ManyToOne
    @JoinColumn( name = "user_id")
    private UserEntity userId;

    public UserEntity getUserId() {
        return userId;
    }



}
