package com.joris.drawsomethingbackend.handlers;

import com.google.gson.Gson;
import com.joris.drawsomethingbackend.components.GameComponent;
import com.joris.drawsomethingbackend.enums.GameMessageType;
import com.joris.drawsomethingbackend.enums.LobbyMessageType;
import com.joris.drawsomethingbackend.models.*;
import com.joris.drawsomethingbackend.services.GameService;
import com.joris.drawsomethingbackend.services.PlayerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    @Setter
    private GameService gameService;

    @Setter
    private GameComponent component;

    @Setter
    private PlayerService playerService;

    public MessageHandler() {
    }

    @Autowired
    public MessageHandler(GameService gameService, GameComponent component, PlayerService playerService) {
        this.gameService = gameService;
        this.component = component;
        this.playerService = playerService;
    }

    public WebsocketLobbyMessage handleLobbyMessage(WebsocketLobbyMessage msg) {
        switch (msg.getType()) {
            case CREATE:
                System.out.println("Create lobby");
                Player player = playerService.getPlayerByUserName(msg.getMessage().toString());
                int id = gameService.createGame(player);
                return new WebsocketLobbyMessage(LobbyMessageType.CREATE,gameService.getGames());
            case CONNECT:
                return new WebsocketLobbyMessage(LobbyMessageType.CONNECT, gameService.getGames());
        }
        throw new UnsupportedOperationException();
    }

    public WebsocketGameMessage handleGameMessage(int gameId, WebsocketGameMessage msg) {
        Game game = gameService.getGame(gameId);
        switch (msg.getType()) {
            case DRAW:
                Location location = new Gson().fromJson(msg.getMessage().toString(),Location.class);
                System.out.println(msg.getMessage());
                return new WebsocketGameMessage(msg.getType(),game.addPoint(location));
            case STOPDRAWING:
                game.stopDrawing();
                return msg;
            case GUESS:
                return new WebsocketGameMessage(GameMessageType.GUESS,game.guessSubject(msg.getMessage().toString()));
            case LEAVE:
                Player player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.LEAVE, game.removePlayer(player));
            case STARTDRAWING:
                location = new Gson().fromJson(msg.getMessage().toString(),Location.class);
                game.startDrawing(location);
                return msg;
            case STARTGAME:
                player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.STARTGAME,component.startGame(game,player));
            case CLEAR:
                game.clearDrawing();
                return msg;
            case CHANGECOLOR:
                String color = msg.getMessage().toString();
                game.setCurrentColor(color);
                return msg;
            case JOIN:
                player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.JOIN, game.addPlayer(player));
            case SETTHICKNESS:
                game.setThickness(Integer.getInteger(msg.getMessage().toString()));
                return msg;
            case SETSUBJECT:
                game.setSubject(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.SETSUBJECT,null);
        }
        throw new UnsupportedOperationException();
    }
}