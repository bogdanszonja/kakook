package com.codecool.kakook.game;

import com.codecool.kakook.util.Countdown;

import java.util.List;

public class Game {

    private static Game instance = new Game();

    private UserController userController = UserController.getInstance();
    private QuestionController questionController = QuestionController.getInstance();
    private AdminController adminController = AdminController.getInstance();

    private Question actualQuestion;
    private final int POINT_FOR_GOOD_ANSWER = 500;

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
        actualQuestion = questionController.getQuestions().iterator().next();
        return actualQuestion;
    }

    public void sendQuestion() {
        List<User> users = userController.getUsers();
        for(User user: users) {
            user.sendQuestion();
        }
        adminController.getAdmin().sendQuestion(nextQuestion());
        Countdown.timer.start();
    }

    public void checkAnswerForUser(String answerNumber, User user) {
        if (answerNumber.equals("answer" + actualQuestion.getGoodAnswerNumber())) {
            user.increasePoints(POINT_FOR_GOOD_ANSWER);
        }
    }
}
