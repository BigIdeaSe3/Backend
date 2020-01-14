package com.joris.drawsomethingbackend.models;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Randomizer implements Callable<List<String>> {

    private List<String> subjects;


    public Randomizer(List<String> array) {
        subjects = array;
    }

    //Knuth shuffle algorithm
    public List<String> randomize(List<String> arr) {
        Random r = new Random();

        for (int i = arr.size()-1; i > 0; i--) {
            int j = r.nextInt(i);

            String tmp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, tmp);
        }

        return arr;
    }

    @Override
    public List<String> call() throws Exception {
        return randomize(subjects);
    }
}
