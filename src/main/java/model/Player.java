package model;

import model.enums.Color;
import model.interfaces.IPlayer;

public class Player implements IPlayer {
    private String name;
    private String sessionId;
    private Color color;
    private int wins;
    private int losses;
    private int draws;

    Player(String name, String sessionId, Color color, int wins, int losses, int draws) {
        this.sessionId = sessionId;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    Player(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}