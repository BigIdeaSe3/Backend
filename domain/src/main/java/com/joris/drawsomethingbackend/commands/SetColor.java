package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Color;
import com.joris.drawsomethingbackend.models.Game;

public class SetColor implements Command {
    private Controller controller;

    public SetColor(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Object execute(Game game, DTO message) {
        game.setCurrentColor((Color) message);
        return null;
    }
}
