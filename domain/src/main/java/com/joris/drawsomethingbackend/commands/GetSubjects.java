package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Randomizer;
import com.joris.drawsomethingbackend.models.Subject;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetSubjects implements Command {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<List<String>> future;

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
