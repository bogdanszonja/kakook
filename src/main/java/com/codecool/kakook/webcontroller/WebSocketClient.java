package com.codecool.kakook.webcontroller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.codecool.kakook.game.*;
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
        System.out.println(session.getRemoteAddress().getHostString() + " connected!");
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        this.session = session;
        super.removeThisFromUserController(this);
        System.out.println(session.getRemoteAddress().getHostString() + " closed!");
    }

    private void sendMessage(String message){
        try {
            session.getRemote().sendString(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String messageHandler(String message){
        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
        if (jsonObject.has("action") && jsonObject.get("action").getAsString().equals("setup_nickname")){
            if (Game.getInstance().isGameStarted()){
                JsonObject response = new JsonObject();
                response.addProperty("action", "setup_nickname");
                response.addProperty("success", false);
                response.addProperty("reason", "game_already_started");
                return response.toString();
            } else{
                super.addThisToUserController(this);
                super.setNickname(jsonObject.get("nickname").getAsString());
                JsonObject response = new JsonObject();
                response.addProperty("action", "setup_nickname");
                response.addProperty("success", true);
                response.addProperty("nickname", jsonObject.get("nickname").getAsString());
                return response.toString();
            }
        }
        else if (jsonObject.has("action") && jsonObject.get("action").getAsString().equals("send_answer")){
            JsonObject response = new JsonObject();
            response.addProperty("action", "send_answer");
            response.addProperty("success", true);
            response.addProperty("answer", jsonObject.get("answer").getAsString());
            String answer = jsonObject.get("answer").getAsString();
            Game.getInstance().increasePoints(answer, this, ZonedDateTime.now());
            return response.toString();
        }
        return null;
    }

    @Override
    public void startGame() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("server_action", "start_game");
        sendMessage(jsonObject.toString());
    }

    @Override
    public void sendQuestion() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("server_action", "new_question_shown");
        sendMessage(jsonObject.toString());
    }

    @Override
    public void answerShown() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("server_action", "answer_shown");
        jsonObject.addProperty("is_answer_good", super.isActualAnswerGood().name());
        jsonObject.addProperty("points", super.getPoints());
        jsonObject.addProperty("rank", Game.getInstance().sendRank(this));
        sendMessage(jsonObject.toString());
    }


}