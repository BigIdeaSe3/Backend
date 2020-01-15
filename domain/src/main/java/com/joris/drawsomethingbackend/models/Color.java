package com.joris.drawsomethingbackend.models;

import com.joris.drawsomethingbackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Color implements DTO {
    private String kleur;

}
