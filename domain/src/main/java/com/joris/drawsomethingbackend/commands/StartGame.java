package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;

public class StartGame implements Command {

    private Controller controller;

    public StartGame(Controller controller) {
        this.controller = controller;
    }

    @Override
    public DTO execute(Game game, DTO message) {
        return game.startGame();
    }
}
