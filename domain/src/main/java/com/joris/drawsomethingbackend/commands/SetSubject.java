package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Subject;

public class SetSubject implements Command {

    @Override
    public DTO execute(Game game, DTO message) {
        game.setSubject((Subject)message);
        return null;
    }
}