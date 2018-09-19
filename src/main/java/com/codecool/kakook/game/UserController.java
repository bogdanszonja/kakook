package com.codecool.kakook.game;

import java.util.ArrayList;
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
        System.out.println(users.size());
        for (User userr:users)
            userr.sendMessage("New user connected");
    }

    public void removeUser(User user){
        users.remove(user);
    }
}
