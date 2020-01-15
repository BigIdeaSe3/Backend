package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.enums.GameMessageType;
import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Randomizer;
import com.joris.drawsomethingbackend.models.Subject;
import com.joris.drawsomethingbackend.models.WebsocketGameMessage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetSubjects implements Command {
    private Controller controller;

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<List<String>> future;

    public GetSubjects(Controller controller) {
        this.controller = controller;
    }


    @Override
    public List<Subject> execute(Game game, DTO message) {
        try {
            game.setSubjects(executorService.submit(new Randomizer(game.getSubjects())).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game.getSubjects().subList(0,3);
    }
}
