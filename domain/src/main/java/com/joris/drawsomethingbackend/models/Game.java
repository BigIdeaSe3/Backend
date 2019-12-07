package com.joris.drawsomethingbackend.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Game {
    @Getter
    private List<Player> connectedPlayers = new ArrayList<>();
    private List<Tuple<String, List<Location>>> locations = new ArrayList<>();
    @Setter
    private String currentColor = "#000000";
    private String subject;
    @Getter
    @Setter
    private Player drawer;
    @Getter
    @Setter
    private Integer thickness;

    public Game(Player player) {
        this.connectedPlayers.add(player);
        this.locations.add(new Tuple<>(currentColor,new ArrayList<>()));
    }

    public List<Player> addPlayer(Player player) {
        connectedPlayers.add(player);
        return connectedPlayers;
    }

    public List<Player> removePlayer(Player player) {
        connectedPlayers.remove(player);
        return connectedPlayers;
    }

    public Location startDrawing(Location location) {
        this.locations.get(locations.size()-1).y.add(location);
        return location;
    }

    public boolean stopDrawing() {
        return this.locations.add(new Tuple<>(currentColor, new ArrayList<>()));
    }

    public Location addPoint(Location location) {
        this.locations.get(locations.size()-1).y.add(location);
        return location;
    }

    public void clearDrawing() {
        this.locations.clear();
    }

    public boolean guessSubject(String guess) {
        return guess.equals(subject);
    }
}
