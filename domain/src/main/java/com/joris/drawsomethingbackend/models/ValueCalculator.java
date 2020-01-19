package com.joris.drawsomethingbackend.models;

import java.util.Arrays;
import java.util.List;

public class ValueCalculator {
    private static ValueCalculator instance = new ValueCalculator();
    private static List<Tuple<Integer, Character>> charValues = Arrays.asList(new Tuple<>(1,'a'), new Tuple<>(1,'b'), new Tuple<>(2,'c'),
            new Tuple<>(1,'d'), new Tuple<>(1,'e'), new Tuple<>(3,'f'), new Tuple<>(1,'g'), new Tuple<>(1,'h'), new Tuple<>(1,'i'),
            new Tuple<>(1,'j'), new Tuple<>(3,'k'), new Tuple<>(2,'l'), new Tuple<>(1,'m'), new Tuple<>(1,'n'), new Tuple<>(1,'o'),
            new Tuple<>(4,'p'), new Tuple<>(1,'q'), new Tuple<>(1,'r'), new Tuple<>(2,'s'), new Tuple<>(1,'t'), new Tuple<>(1,'u'),
            new Tuple<>(1,'v'), new Tuple<>(4,'w'), new Tuple<>(5,'x'), new Tuple<>(5,'y'), new Tuple<>(3,'z'));

    public static ValueCalculator getInstance() {
        return instance;
    }

    public ValueCalculator() {
    }

    public int calculateValue(String onderwerp) {
        int val = 0;
        for (Character letter: onderwerp.toCharArray()) {
            for (Tuple<Integer, Character> tuple: charValues) {
                if (letter.equals(tuple.y)) {
                    val += tuple.x;
                }
            }
        }
        return val;
    }

}
