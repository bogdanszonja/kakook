package com.codecool.kakook.game;

import java.util.Collections;
import java.util.List;

public class Question {

    private String description;
    private Answer goodAnswer;
    private List<Answer> allAnswers;

    public Question(String description, Answer goodAnswer, List<Answer> allAnswers) {
        this.description = description;
        this.goodAnswer = goodAnswer;
        Collections.shuffle(allAnswers);
        this.allAnswers = allAnswers;
    }

    public String getDescription() {
        return description;
    }

    public int getGoodAnswerNumber() {
        return allAnswers.indexOf(goodAnswer);
    }

    public List<Answer> getAllAnswers() {
        return allAnswers;
    }

}
