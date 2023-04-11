package com.saatchiart.server.ws;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Datahandler extends TextWebSocketHandler{
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
            log.info("Message: {} ",message.getPayload());
            session.sendMessage(new TextMessage("Vui lòng chờ"));
    }
}
