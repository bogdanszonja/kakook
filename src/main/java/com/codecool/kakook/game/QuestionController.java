package com.codecool.kakook.game;

import java.util.ArrayList;
import java.util.List;

public class QuestionController {

    private List<Question> questions = new ArrayList<>();

    private static QuestionController instance = new QuestionController();

    private QuestionController() {

    }

    public static QuestionController getInstance() {
        return instance;
    }

    private void getActualQuestion() {

    }

    private void randomizeQuestions() {

    }
}
