package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JoinColumn(name = "EDV") 
    private UserEntity EDV;


    public void setEDV(UserEntity EDV) {
        this.EDV = EDV;
    }

    public UserEntity getEDV() {
        return EDV;
    }

        /*
     * Conectando a pergunta com suas respostas // Feito na Integração back front *
    */

    @OneToMany(mappedBy = "questionId")
    private List<AnswerEntity> answers;

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }
}
