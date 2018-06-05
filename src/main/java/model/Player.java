package model;

import model.enums.TeamColor;
import model.interfaces.IPlayer;

public class Player extends Entity implements IPlayer {
    private String name;
    private String password;
    private TeamColor teamColor;
    private int wins;
    private int losses;
    private int draws;
    private int ranking;

    Player(String name, String password){
        this.name = name;
        this.password = password;
    }
    Player(String name, int wins, int losses, int draws, int ranking) {
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