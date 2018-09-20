package com.codecool.kakook.game;

import com.codecool.kakook.util.Countdown;

import java.util.List;

public class Game {

    private static Game instance = new Game();

    private UserController userController = UserController.getInstance();
    private QuestionController questionController = QuestionController.getInstance();
    private AdminController adminController = AdminController.getInstance();



    private Game() {}

    public static Game getInstance() {
        return instance;
    }

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
        Countdown.timer.start();
    }

}
