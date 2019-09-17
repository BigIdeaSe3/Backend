package com.joris.drawsomethingbackend.websocket.controllers;

import com.joris.drawsomethingbackend.models.WebsocketMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller
public class GameController {

    @MessageMapping("/game/{lobbyId}/startGame")
    @SendTo("/topic/game/{lobbyId}/startGame")
    public WebsocketMessage startGame(){
        throw new NotImplementedException();
    }

    @MessageMapping("/game/{lobbyId}/startDrawing")
    @SendTo("/topic/game/{lobbyId}/startDrawing")
    public WebsocketMessage startDrawing(@DestinationVariable("lobbyId") String lobbyId, WebsocketMessage msg){
        System.out.println("/topic/game/"+lobbyId+"/startDrawing");
        return new WebsocketMessage(msg.getMessage());
    }

    @MessageMapping("/game/{lobbyId}/addPoint")
    @SendTo("/topic/game/{lobbyId}/newPoint")
    public WebsocketMessage drawPoint(@DestinationVariable("lobbyId") String lobbyId, WebsocketMessage msg){
        System.out.println(msg.getMessage());
        return new WebsocketMessage(msg.getMessage());


    }

    @MessageMapping("/game/{lobbyId}/stopDrawing")
    @SendTo("/topic/game/{lobbyId}/stopDrawing")
    public WebsocketMessage stopDrawing(@DestinationVariable("lobbyId") String lobbyId, WebsocketMessage msg){
        System.out.println("/topic/game/"+lobbyId+"/stopDrawing");
        return new WebsocketMessage(msg.getMessage());
    }


    @MessageMapping("/game/{lobbyId}/guessWord")
    @SendTo("/topic/game")
    public WebsocketMessage guessWord(@DestinationVariable("lobbyId") String lobbyId, WebsocketMessage msg){
        return new WebsocketMessage(true);
    }

}
