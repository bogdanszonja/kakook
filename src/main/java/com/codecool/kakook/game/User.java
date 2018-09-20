package com.codecool.kakook.game;


public abstract class User {

    private String nickname;

    private int points = 0;

    private boolean isActualAnswerGood;

    public boolean isActualAnswerGood() {
        return isActualAnswerGood;
    }

    public void setActualAnswerGood(boolean actualAnswerGood) {
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

    public void addThisToUserController(){
        UserController.getInstance().addUser(this);
    }

    public void removeThisFromUserController(){
        UserController.getInstance().removeUser(this);
    }

    public abstract void startGame();

    public abstract void sendQuestion();

    public abstract void answerShown();
}
