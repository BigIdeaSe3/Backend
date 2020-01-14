package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;

public class ClearDrawing implements Command {

    private Controller controller;

    public ClearDrawing(Controller controller) {
        this.controller = controller;
    }

    @Override
    public DTO execute(Game game, DTO message) {
        game.clearDrawing();
        return null;
    }
}
