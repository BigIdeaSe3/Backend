package com.joris.drawsomethingbackend.handlers;

import com.google.gson.Gson;
import com.joris.drawsomethingbackend.commands.*;
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

import java.util.EnumMap;

@Component
public class WebsocketMessageHandler implements com.joris.drawsomethingbackend.interfaces.MessageHandler {


    private final EnumMap<GameMessageType, Command> commandHashMap = new EnumMap<>(GameMessageType.class);
    private Gson gson = new Gson();

    @Setter
    private GameService gameService;

    @Setter
    private PlayerService playerService;



    public WebsocketMessageHandler() {
        registerCommands();
    }

    @Autowired
    public WebsocketMessageHandler(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
        registerCommands();
    }

    private void registerCommands(){
        register(GameMessageType.STARTDRAWING, new StartDrawing());
        register(GameMessageType.DRAW,new AddPoint());
        register(GameMessageType.STOPDRAWING, new StopDrawing());
        register(GameMessageType.JOIN,new JoinGame());
        register(GameMessageType.GETALLPLAYERS, new GetAllPlayers());
        register(GameMessageType.CLEAR, new ClearDrawing());
        register(GameMessageType.LEAVE, new LeaveGame());
        register(GameMessageType.CHANGECOLOR, new SetColor());
        register(GameMessageType.SETTHICKNESS, new SetThickness());
        register(GameMessageType.SETSUBJECT, new SetSubject());
        register(GameMessageType.GETSUBJECTS, new GetSubjects());
        register(GameMessageType.GUESS, new GuessSubject());
        register(GameMessageType.STARTGAME, new StartGame());
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
        DTO dto = deserializeDTO(message.getType(), message.getMessage().toString());
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

            case CHANGECOLOR:
                return gson.fromJson(dtoMessage, Color.class);
            case SETTHICKNESS:
                return gson.fromJson(dtoMessage, Thickness.class);
            case SETSUBJECT:
            case GUESS:
                return gson.fromJson(dtoMessage, Subject.class);
            case GETALLPLAYERS:
            case STOPDRAWING:
            case CLEAR:
            case STARTGAME:
            case GETSUBJECTS:
            default:
                return null;
        }
    }
}
