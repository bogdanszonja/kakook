package com.codecool.kakook.game;

import java.util.List;

public class Game {

    private UserController userController = UserController.getInstance();
    private QuestionController questionController = QuestionController.getInstance();

    public void startGame() {
        questionController.createQuestions();
        List<User> users = userController.getUsers();
        for(User user: users) {
            user.startGame();
        }
    }

    public Question nextQuestion() {
        return questionController.getQuestions().peekFirst();
    }

    public void sendQuestion() {
        List<User> users = userController.getUsers();
        for(User user: users) {
            user.sendQuestion();
        }
    }
}
