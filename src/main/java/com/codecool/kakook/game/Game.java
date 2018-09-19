package com.codecool.kakook.game;

public class Game {

    private UserController userController = UserController.getInstance();
    private QuestionController questionController = QuestionController.getInstance();

    public void startGame() {
        questionController.createQuestions();
    }

    public Question nextQuestion() {
        return questionController.getQuestions().peekFirst();
    }
}
