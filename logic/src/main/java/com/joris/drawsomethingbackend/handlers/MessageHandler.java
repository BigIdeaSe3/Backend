package com.joris.drawsomethingbackend.handlers;

import com.google.gson.Gson;
import com.joris.drawsomethingbackend.commands.*;
import com.joris.drawsomethingbackend.controllers.GameController;
import com.joris.drawsomethingbackend.enums.GameMessageType;
import com.joris.drawsomethingbackend.enums.LobbyMessageType;
import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.*;
import com.joris.drawsomethingbackend.services.GameService;
import com.joris.drawsomethingbackend.services.PlayerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class MessageHandler implements com.joris.drawsomethingbackend.interfaces.MessageHandler {


    private final HashMap<GameMessageType, Command> commandHashMap = new HashMap<>();
    private DTO dto;
    private Gson gson = new Gson();
    private GameController game;

    private Instant time;

    private int counter = 0;

    @Setter
    private GameService gameService;

    @Setter
    private PlayerService playerService;



    public MessageHandler() {
        registerCommands();
    }

    @Autowired
    public MessageHandler(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
        registerCommands();
    }

    private void registerCommands(){
        register(GameMessageType.STARTDRAWING, new StartDrawing(game));
        register(GameMessageType.DRAW,new AddPoint(game));
        register(GameMessageType.STOPDRAWING, new StopDrawing(game));
        register(GameMessageType.JOIN,new JoinGame(game));
        register(GameMessageType.GETALLPLAYERS, new GetAllPlayers(game));
        register(GameMessageType.CLEAR, new ClearDrawing(game));
        register(GameMessageType.LEAVE, new LeaveGame(game));
        register(GameMessageType.CHANGECOLOR, new SetColor(game));
        register(GameMessageType.SETTHICKNESS, new SetThickness(game));
        register(GameMessageType.SETSUBJECT, new SetSubject(game));
        register(GameMessageType.GETSUBJECTS, new GetSubjects(game));
        register(GameMessageType.GUESS, new GuessSubject(game));
        register(GameMessageType.STARTGAME, new StartGame(game));
    }


    public WebsocketLobbyMessage handleLobbyMessage(WebsocketLobbyMessage msg) {
        switch (msg.getType()) {
            case CREATE:
                System.out.println("Create lobby");
                int id = gameService.createGame();
                return new WebsocketLobbyMessage(LobbyMessageType.CREATE,gameService.getGame(id));
            case CONNECT:
                return new WebsocketLobbyMessage(LobbyMessageType.CONNECT, gameService.getGames());
        }
        throw new UnsupportedOperationException();
    }

    public void register(GameMessageType commandName, Command command) {
        commandHashMap.put(commandName,command);
    }


    @Override
    public WebsocketGameMessage execute(WebsocketGameMessage message, Integer id) {
        GameMessageType commandName = message.getType();
        Command cmd = commandHashMap.get(commandName);
        if (cmd == null) throw new IllegalStateException("No command registered for " + commandName);
        dto = deserializeDTO(message.getType(), message.getMessage().toString());
        return new WebsocketGameMessage(message.getType(),cmd.execute(gameService.getGame(id), dto));
    }

    public DTO deserializeDTO(GameMessageType type, String dtoMessage) {

        switch (type) {
            case DRAW:
            case STARTDRAWING:
                return gson.fromJson(dtoMessage, Location.class);
            case JOIN:
            case LEAVE:
                return playerService.getPlayerByUserName(dtoMessage);
            case GETALLPLAYERS:
            case STOPDRAWING:
            case CLEAR:
            case STARTGAME:
                return null;
            case CHANGECOLOR:
                return gson.fromJson(dtoMessage, Color.class);
            case SETTHICKNESS:
                return gson.fromJson(dtoMessage, Thickness.class);
            case SETSUBJECT:
            case GUESS:
                return gson.fromJson(dtoMessage, Subject.class);

        }
        return null;
    }
}
