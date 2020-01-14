package com.joris.drawsomethingbackend.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joris.drawsomethingbackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player implements DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize
    public Long id;
    public String username;
    public String password;
    public Long points = 0L;
    @Nullable
    public String email;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
