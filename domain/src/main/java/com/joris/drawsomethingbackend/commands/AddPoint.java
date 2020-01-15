package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Location;

public class AddPoint implements Command {

    @Override
    public DTO execute(Game game, DTO message) {
        Location loc = (Location)message;
        return game.addPoint(loc);
    }
}
