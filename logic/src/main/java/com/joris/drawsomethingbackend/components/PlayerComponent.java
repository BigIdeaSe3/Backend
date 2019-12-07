package com.joris.drawsomethingbackend.components;

import com.joris.drawsomethingbackend.interfaces.CRUD;
import com.joris.drawsomethingbackend.models.Player;
import com.joris.drawsomethingbackend.services.PlayerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerComponent implements CRUD<Player,Long> {


    @Setter
    private PlayerService service;

    public PlayerComponent() {
    }

    @Autowired
    public PlayerComponent(PlayerService service) {
        this.service = service;
    }

    @Override
    public Player create(Player entity) {
        return service.create(entity);
    }

    @Override
    public Player read(Long id) {
        return service.read(id);
    }

    @Override
    public Iterable<Player> readAll() {
        return service.readAll();
    }

    @Override
    public Player update(Player entity) {
        return service.update(entity);
    }

    @Override
    public boolean delete(Long id) {
        return service.delete(id);
    }

    public Player login(String username, String password) {
        return service.getPlayerByUserNameAndPassword(username,password);
    }
}
