package com.joris.drawsomethingbackend.services;

import com.joris.drawsomethingbackend.interfaces.CRUD;
import com.joris.drawsomethingbackend.models.Player;
import com.joris.drawsomethingbackend.repositories.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements CRUD<Player,Long> {

    @Autowired
    @Qualifier("PlayerRepository")
    private IPlayerRepository repository;

    public Player getPlayerByUserName(String username) {
        return repository.getPlayerByUsername(username);
    }

    public Player getPlayerByUserNameAndPassword(String username, String password) {
        return repository.getPlayerByUsernameAndPassword(username, password);
    }

    @Override
    public Player create(Player entity) {
        return repository.save(entity);
    }

    @Override
    public Player read(Long id) {
        Optional<Player> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Iterable<Player> readAll() {
        return repository.findAll();
    }

    @Override
    public Player update(Player entity) {
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
