package com.codecool.kakook.webcontroller;

import java.io.IOException;

import com.codecool.kakook.game.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketClient extends User {

    private Session session;

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("Message received:" + message);
        if (session.isOpen()) {
            this.session = session;
            String response = messageHandler(message);
            session.getRemote().sendString(response);
        }
    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        this.session = session;
        super.addThisToUserController();
        System.out.println(session.getRemoteAddress().getHostString() + " connected!");
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        this.session = session;
        super.removeThisFromUserController();
        System.out.println(session.getRemoteAddress().getHostString() + " closed!");
    }

    public void sendMessage(String message){
        try {
            session.getRemote().sendString(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String messageHandler(String message){
        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
        if (jsonObject.has("action") && jsonObject.get("action").getAsString().equals("setup_nickname")){
            super.setNickname(jsonObject.get("nickname").getAsString());
            JsonObject response = new JsonObject();
            response.addProperty("action", "setup_nickname");
            response.addProperty("success", true);
            response.addProperty("nickname", jsonObject.get("nickname").getAsString());
            return response.toString();
        }
        return null;
    }

}