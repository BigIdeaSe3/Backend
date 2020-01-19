package com.joris.drawsomethingbackend.models;

import java.util.Comparator;

public class SubjectSorter implements Comparator<Subject> {
    @Override
    public int compare(Subject o1, Subject o2) {
        return o1.getValue() - o2.getValue();
    }
}
