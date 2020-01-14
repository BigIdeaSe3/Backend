package com.joris.drawsomethingbackend.models;

import com.joris.drawsomethingbackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subject implements DTO {
    private String subject;

    public boolean compare(Subject guess) {
        return this.subject.equals(guess.getSubject());
    }
}
