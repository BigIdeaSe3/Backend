package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;

public class StopDrawing implements Command {
    @Override
    public Boolean execute(Game game, DTO message) {
        return game.stopDrawing();
    }
}
