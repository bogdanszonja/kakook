package com.codecool.kakook.webcontroller;

import javax.servlet.annotation.WebServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebServlet(urlPatterns="/adminwebsocket")
public class WebSocketClientAdminEndpoint extends WebSocketServlet{

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(WebSocketClientAdmin.class);
    }

}