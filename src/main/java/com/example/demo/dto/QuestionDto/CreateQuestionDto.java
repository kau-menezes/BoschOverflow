package com.example.demo.dto.QuestionDto;

public record CreateQuestionDto(
    String questionTitle,
    String questionText,
    Long spaceId,
    Long userId
) {}
