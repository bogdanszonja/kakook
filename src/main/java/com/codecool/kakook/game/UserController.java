package com.codecool.kakook.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserController {
    private static UserController instance = new UserController();
    private List<User> users = new ArrayList<>();

    private UserController(){}

    public static UserController getInstance(){
        return instance;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public int calculateRank(User user) {
        List<User> newList = new ArrayList<>(users);
        newList.sort(Comparator.comparing(User::getPoints));
        return users.indexOf(user) + 1;
    }
}
