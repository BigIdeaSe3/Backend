package com.joris.drawsomethingbackend.websocket.controllers;

import com.joris.drawsomethingbackend.models.WebsocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller
@Slf4j
public class LobbyController {

    @MessageMapping("/lobbies/{lobbyId}/join")
    @SendToUser("/topic/reply/lobbies/join")
    public WebsocketMessage joinLobby(@DestinationVariable("lobbyId") String lobbyId, WebsocketMessage msg){
        log.info(String.valueOf(lobbyId));
        log.info(msg.getMessage().toString());
        return new WebsocketMessage(msg.getMessage());
    }

    @MessageMapping("/lobbies")
    @SendToUser("/topic/reply/lobbies")
    public WebsocketMessage getAllLobbies(){
        throw new NotImplementedException();
    }
}
