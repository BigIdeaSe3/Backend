package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Subject;

public class GuessSubject implements Command {

    private Controller controller;

    public GuessSubject(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Boolean execute(Game game, DTO message) {
        return game.getSubject().compare((Subject)message);
    }
}