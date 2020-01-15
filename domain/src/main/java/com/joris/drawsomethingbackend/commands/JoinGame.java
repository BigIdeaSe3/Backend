package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Player;

import java.util.List;

public class JoinGame implements Command {
    @Override
    public List<Player> execute(Game game, DTO message) {
        return game.addPlayer((Player)message);
    }
}
