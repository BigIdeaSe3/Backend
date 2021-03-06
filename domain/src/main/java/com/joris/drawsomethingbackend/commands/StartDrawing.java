package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Location;

public class StartDrawing implements Command {

    @Override
    public Location execute(Game game, DTO message) {
        return game.startDrawing((Location)message);
    }
}
