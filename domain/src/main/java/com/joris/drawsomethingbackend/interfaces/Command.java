package com.joris.drawsomethingbackend.interfaces;

import com.joris.drawsomethingbackend.models.Game;

public interface Command {
    Object execute(Game game, DTO message);
}
