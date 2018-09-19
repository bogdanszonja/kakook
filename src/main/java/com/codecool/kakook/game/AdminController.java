package com.codecool.kakook.game;

import com.codecool.kakook.webcontroller.WebSocketClientAdmin;

public class AdminController {
    private static AdminController instance = new AdminController();
    private WebSocketClientAdmin admin = null;

    private AdminController() {
    }

    public static AdminController getInstance(){
        return instance;
    }

    public WebSocketClientAdmin getAdmin(){
        return this.admin;
    }

    public void setAdmin(WebSocketClientAdmin admin){
        if (this.admin == null)
            this.admin = admin;
    }

    public void removeAdmin(){
        this.admin = null;
    }

    public boolean isAdminSet(){
        if (admin == null)
            return false;
        return true;
    }
}
