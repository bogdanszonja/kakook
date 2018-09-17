package com.codecool.kakook;

import java.util.List;

public class Question {

    private String description;
    private String goodAnswer;
    private List<String> wrongAnswers;

    public Question(String description, String goodAnswer, List<String> wrongAnswers) {
        this.description = description;
        this.goodAnswer = goodAnswer;
        this.wrongAnswers = wrongAnswers;
    }
}
