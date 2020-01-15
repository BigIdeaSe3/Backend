package com.joris.drawsomethingbackend.models;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Randomizer implements Callable<List<Subject>> {
    SecureRandom r = new SecureRandom();
     private List<Subject> subjects;


    public Randomizer(List<Subject> array) {
        subjects = array;
    }

    //Knuth shuffle algorithm
    public List<Subject> randomize(List<Subject> arr) {

        for (int i = arr.size()-1; i > 0; i--) {
            int j = r.nextInt(i);

            Subject tmp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, tmp);
        }

        return arr;
    }

    @Override
    public List<Subject> call() throws Exception {
        return randomize(subjects);
    }
}
