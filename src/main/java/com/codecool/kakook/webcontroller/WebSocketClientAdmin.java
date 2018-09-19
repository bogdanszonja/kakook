package com.codecool.kakook.webcontroller;

import java.io.IOException;

import com.codecool.kakook.game.AdminController;
import com.codecool.kakook.game.Question;
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
public class WebSocketClientAdmin {

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
        AdminController.getInstance().setAdmin(this);
        System.out.println(session.getRemoteAddress().getHostString() + " connected!");
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        this.session = session;
        AdminController.getInstance().removeAdmin();
        System.out.println(session.getRemoteAddress().getHostString() + " closed!");
    }

    private void sendMessage(String message){
        try {
            session.getRemote().sendString(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendNewNickname(String nickname){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("server_action", "new_nickname");
        jsonObject.addProperty("nickname", nickname);
        sendMessage(jsonObject.toString());
    }

    private String messageHandler(String message){
        return null;
    }

    public void sendQuestion(Question question) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "new_question");
        jsonObject.addProperty("description", question.getDescription());
        jsonObject.addProperty("good_answer", question.getGoodAnswer().toString());
        jsonObject.addProperty("answers", question.getAllAnswers().toString());
        sendMessage(jsonObject.toString());
    }
}