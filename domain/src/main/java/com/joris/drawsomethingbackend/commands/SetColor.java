package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Color;
import com.joris.drawsomethingbackend.models.Game;

public class SetColor implements Command {

    @Override
    public Object execute(Game game, DTO message) {
        game.setCurrentColor((Color) message);
        return message;
    }
}
