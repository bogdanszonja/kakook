package com.codecool.kakook.game;

public class User {

    private int id;
    private static int idCounter = 1;
    private String nickName;
    private int score;

    public User(String nickName, int score) {
        this.nickName = nickName;
        this.score = score;
        this.id = idCounter;
        idCounter++;
    }
}
