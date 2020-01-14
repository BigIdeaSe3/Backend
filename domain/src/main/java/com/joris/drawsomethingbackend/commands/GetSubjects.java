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
        new Randomizer(subjects);
        return subjects.subList(0,3);
//        try {
//            if (future != null) {
//                subjects = future.get();
//                executorService.shutdown();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        future = executorService.submit(new Randomizer(subjects));
//        return new WebsocketGameMessage(GameMessageType.GETSUBJECTS, subjects.subList(0,3));
    }
}
