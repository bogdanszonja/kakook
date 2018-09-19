package com.codecool.kakook.game;

import java.util.List;

public class Game {

    private UserController userController = UserController.getInstance();
    private QuestionController questionController = QuestionController.getInstance();
    private AdminController adminController = AdminController.getInstance();

    public void startGame() {
        questionController.createQuestions();
        List<User> users = userController.getUsers();
        for(User user: users) {
            user.startGame();
        }
        adminController.getAdmin().startGame();
    }

    private Question nextQuestion() {
        return questionController.getQuestions().iterator().next();
    }

    public void sendQuestion() {
        List<User> users = userController.getUsers();
        for(User user: users) {
            user.sendQuestion();
        }
        adminController.getAdmin().sendQuestion(nextQuestion());
    }
}
