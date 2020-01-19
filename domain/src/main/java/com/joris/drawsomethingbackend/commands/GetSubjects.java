package com.joris.drawsomethingbackend.commands;

import com.joris.drawsomethingbackend.interfaces.Command;
import com.joris.drawsomethingbackend.interfaces.DTO;
import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Randomizer;
import com.joris.drawsomethingbackend.models.Subject;
import com.joris.drawsomethingbackend.models.SubjectSorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class GetSubjects implements Command {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    List<FutureTask<List<Subject>>> futures = new ArrayList<>();

    @Override
    public List<Subject> execute(Game game, DTO message) {
        List<List<Subject>> subjectsList = Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        try {
            game.getSubjects().sort(new SubjectSorter());


            int index = 0;


            for (int i = 0; i < game.getSubjects().size(); i++) {
                Subject subject = game.getSubjects().get(i);
                if (subject.getValue() < 5) {
                    subjectsList.get(0).add(subject);
                } else if (subject.getValue() >= 5 && subject.getValue() < 10) {
                    subjectsList.get(1).add(subject);
                } else {
                    subjectsList.get(2).add(subject);
                }

            }

            System.out.println(subjectsList);


            futures.clear();
            for (int i = 0; i < subjectsList.size(); i++) {
                futures.add((FutureTask<List<Subject>>) executorService.submit(new Randomizer(subjectsList.get(i))));
                subjectsList.get(i).addAll(futures.get(i).get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(subjectsList.get(0).get(0), subjectsList.get(1).get(0), subjectsList.get(2).get(0));
    }
}
