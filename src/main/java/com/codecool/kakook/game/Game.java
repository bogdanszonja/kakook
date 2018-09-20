package com.codecool.kakook.game;

import com.codecool.kakook.util.Countdown;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.List;

public class Game {

    private static Game instance = new Game();

    private UserController userController = UserController.getInstance();
    private QuestionController questionController = QuestionController.getInstance();
    private AdminController adminController = AdminController.getInstance();

    private Question actualQuestion;
    private ZonedDateTime timeOfQuestion = null;

    private final int POINT_FOR_GOOD_ANSWER = 600;

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
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        sendQuestion();
    }

    private Question nextQuestion() {
        actualQuestion = questionController.getQuestions().poll();
        return actualQuestion;
    }

    public void sendQuestion() {
        List<User> users = userController.getUsers();
        for(User user: users) {
            user.sendQuestion();
        }
        adminController.getAdmin().sendQuestion(nextQuestion());
        Countdown.timer.start();
        timeOfQuestion = ZonedDateTime.now();

    }

    public void increasePoints(String answerNumber, User user, ZonedDateTime timeOfAnswer) {
        if (answerNumber.equals("answer" + actualQuestion.getGoodAnswerNumber())) {
            user.setActualAnswerGood(true);
            System.out.println("time of question: " + timeOfQuestion + ", time of answer: " + timeOfAnswer + " kivonva duration: " + Duration.between(timeOfQuestion, timeOfAnswer).getNano() / 1000000);
            Duration duration = Duration.between(timeOfQuestion, timeOfAnswer);
            int secondsPassed = duration.getNano() / 10000000;
            user.increasePoints(POINT_FOR_GOOD_ANSWER - secondsPassed);
        } else {
            user.setActualAnswerGood(false);
        }
    }

    public void sendAnswer() {
        AdminController.getInstance().getAdmin().sendAnswer(actualQuestion.getGoodAnswerNumber());
        for(User user: userController.getUsers()) {
            user.answerShown();
        }
    }

    public int sendRank(User user) {
        return userController.calculateRank(user);
    }
}