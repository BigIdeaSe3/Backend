package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;

public class StartGame implements Command {

    @Override
    public DTO execute(Game game, DTO message) {
        return game.startGame();
    }
}
