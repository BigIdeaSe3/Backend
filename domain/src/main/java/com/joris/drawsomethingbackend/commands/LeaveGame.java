package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Player;

public class LeaveGame implements Command {

    private Controller controller;

    public LeaveGame(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Object execute(Game game, DTO message) {
        return game.removePlayer((Player) message);
    }
}
