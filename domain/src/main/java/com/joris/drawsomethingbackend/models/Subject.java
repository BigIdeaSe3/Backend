package com.joris.drawsomethingbackend.models;

import com.joris.drawsomethingbackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subject implements DTO {
    private String onderwerp;

    public boolean guess(Subject guess) {
        return this.onderwerp.equals(guess.getOnderwerp());
    }
}
