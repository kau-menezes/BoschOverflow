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
 * Essa é a entidade da pergunta, quando uma pergunta foi feita, é aqui que ela deve ser salva.
 * Aqui a conexão é feita com o usuário e com o espaço em que a pergunta foi feita.
*/

@Entity
@Table(name = "tbQuestion") // Mudando o nome da tabela 
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    public Long getQuestionId() {
        return questionId;
    }

    @Column
    public String questionTitle;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Column
    public String questionText;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /*
     * Conectando a pergunta com o espaço em que ela foi feita 
    */

    @ManyToOne
    @JoinColumn( name = "space_id")
    private SpaceEntity spaceId;

    public void setSpaceId(SpaceEntity spaceId) {
        this.spaceId = spaceId;
    }

    public SpaceEntity getSpaceId() {
        return spaceId;
    }

    /*
     * Conectando a pergunta com o usuário que fez ela
    */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    public UserEntity getUserId() {
        return userId;
    }



}
