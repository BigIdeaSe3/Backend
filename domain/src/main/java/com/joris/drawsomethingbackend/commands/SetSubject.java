package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Subject;

public class SetSubject implements Command {

    private Controller controller;

    public SetSubject(Controller controller) {
        this.controller = controller;
    }


    @Override
    public DTO execute(Game game, DTO message) {
        game.setSubject((Subject)message);
        return null;
    }
}