package com.joris.drawsomethingbackend.controllers;

import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.models.Game;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Controller {
    List<Game> games = new ArrayList<>();

    public Game getGame(int id) {
        return games.get(id);
    }

    public List<Game> getGames() {
        return games;
    }

    public int createGame() {
        games.add(new Game());
        return games.size()-1;
    }

}
