package com.anil.quizservice.entity;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private int numQ;
    private String quizTitle;
}
