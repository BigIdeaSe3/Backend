package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.enums.GameMessageType;
import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.Controller;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Randomizer;
import com.joris.drawsomethingbackend.models.WebsocketGameMessage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetSubjects implements Command {
    private Controller controller;

    private List<String> subjects = Arrays.asList("Appel","Peer", "Banaan", "Kaas", "Kapsalon");
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<List<String>> future;

    public GetSubjects(Controller controller) {
        this.controller = controller;
    }


    @Override
    public Object execute(Game game, DTO message) {
        try {
            subjects = executorService.submit(new Randomizer(subjects)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects.subList(0,3);
    }
}
