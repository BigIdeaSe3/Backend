package com.joris.drawsomethingbackend.websocket.controllers;

import com.joris.drawsomethingbackend.handlers.MessageHandler;
import com.joris.drawsomethingbackend.models.WebsocketLobbyMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbyController {

    @Setter
    private final MessageHandler handler;

    @Autowired
    public LobbyController(MessageHandler handler) {
        this.handler = handler;
    }

    @MessageMapping("/lobbies")
    @SendTo("/topic/lobbies")
    public WebsocketLobbyMessage handleMessage(WebsocketLobbyMessage msg) {
        return handler.handleLobbyMessage(msg);
    }

}
