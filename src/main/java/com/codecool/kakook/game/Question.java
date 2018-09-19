package com.codecool.kakook.game;

import java.util.List;

public class Question {

    private String description;
    private Answer goodAnswer;
    private List<Answer> allAnswers;

    public Question(String description, Answer goodAnswer, List<Answer> allAnswers) {
        this.description = description;
        this.goodAnswer = goodAnswer;
        this.allAnswers = allAnswers;
    }
}
