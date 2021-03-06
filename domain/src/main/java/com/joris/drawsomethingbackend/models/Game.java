package com.joris.drawsomethingbackend.models;

import com.joris.drawsomethingbackend.interfaces.DTO;
import lombok.Data;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Game implements DTO {
    private List<Player> connectedPlayers = new ArrayList<>();
    private List<Tuple<Color, List<Location>>> locations = new ArrayList<>();
    private Color currentColor = new Color("#000000");
    private Subject subject;
    private List<Subject> subjects = Arrays.asList(new Subject("appel"), new Subject("peer"), new Subject("banaan"), new Subject("kapsalon"), new Subject("kaas"), new Subject("runescape"), new Subject("zon"), new Subject("goudvis"), new Subject("xi"),new Subject("das"),new Subject("jas"), new Subject("vis"));
    private Player drawer;
    private Thickness thickness;

    public Game(Player player) {
        this.connectedPlayers.add(player);
        this.locations.add(new Tuple<>(currentColor,new ArrayList<>()));
    }

    public Game() {
        this.locations.add(new Tuple<>(currentColor,new ArrayList<>()));
    }

    public List<Player> addPlayer(Player player) {
        if (!connectedPlayers.contains(player)) {
            connectedPlayers.add(player);
            return connectedPlayers;
        } else return null;
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
        if (locations.size() > 0) {
            this.locations.get(locations.size()-1).y.add(location);
            return location;
        } else
            return null;
    }

    public void clearDrawing() {
        this.locations.clear();
    }

    public Player startGame() {
        Player p = connectedPlayers.get(new SecureRandom().nextInt(connectedPlayers.size()));
        setDrawer(p);
        return p;
    }
}
