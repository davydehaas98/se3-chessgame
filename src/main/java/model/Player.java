package model;

import model.enums.Color;

public class Player {
    private int id;
    private String name;
    private Color color;
    private int wins;
    private int losses;
    private int draws;

    Player(int id, String name, Color color, int wins, int losses, int draws) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }
}