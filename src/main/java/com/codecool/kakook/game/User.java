package com.codecool.kakook.game;


import com.codecool.kakook.webcontroller.WebSocketClient;

public abstract class User {

    private String nickname;

    private int points = 0;

    private ActualAnswer isActualAnswerGood;

    public ActualAnswer isActualAnswerGood() {
        return isActualAnswerGood;
    }

    public void setActualAnswerGood(ActualAnswer actualAnswerGood) {
        isActualAnswerGood = actualAnswerGood;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        AdminController.getInstance().getAdmin().sendNewNickname(nickname);
    }

    public void increasePoints(int points){
        this.points += points;
    }

    public void addThisToUserController(WebSocketClient user){
        UserController.getInstance().addUser(user);
    }

    public void removeThisFromUserController(WebSocketClient user){
        UserController.getInstance().removeUser(user);
    }

    public abstract void startGame();

    public abstract void sendQuestion();

    public abstract void answerShown();
}
