package model;

import model.enums.TeamColor;

public class Player extends Entity {
    private String name;
    private String password;
    private TeamColor teamColor;
    private int wins;
    private int losses;
    private int draws;
    private int ranking;

    public Player(String name, String password){
        this.name = name;
        this.password = password;
    }
    public Player(String name, int wins, int losses, int draws, int ranking) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public String getPassword() {
        return password;
    }

    public void setTeamColor(TeamColor teamColor) {
        this.teamColor = teamColor;
    }
}