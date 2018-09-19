package com.codecool.kakook.game;


public abstract class User {

    private String nickname;

    private int points = 0;

    public String getNickname() {
        return nickname;
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

    public abstract void sendMessage(String message);
}
