package com.joris.drawsomethingbackend.websocket.controllers;

import com.joris.drawsomethingbackend.handlers.WebsocketMessageHandler;
import com.joris.drawsomethingbackend.models.WebsocketGameMessage;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@NoArgsConstructor
public class GameController {

    @Setter
    private WebsocketMessageHandler handler;

    @Autowired
    public GameController(WebsocketMessageHandler handler) {
        this.handler = handler;
    }

    @MessageMapping("/game/{lobbyId}")
    @SendTo("/topic/game/{lobbyId}")
    public WebsocketGameMessage handleMessage(@DestinationVariable("lobbyId") Integer lobbyId, WebsocketGameMessage msg) {
        return handler.execute(msg, lobbyId);
    }



}
