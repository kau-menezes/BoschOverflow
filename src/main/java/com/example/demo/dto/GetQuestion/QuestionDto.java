package com.example.demo.dto.GetQuestion;

import java.util.List;

import com.example.demo.dto.AnswerDto.AnswerDto;

public record QuestionDto(
    Long questionId,
    String questionTitle,
    String questionText,
    List<AnswerDto> answers,
    String userName
) {}
