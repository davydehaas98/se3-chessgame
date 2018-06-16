package model;

import model.enums.TeamColor;

public class Player extends Entity {
    private String name;
    private TeamColor teamColor;
    private int wins;
    private int losses;
    private int draws;
    private int ranking;

    public Player(long entityId, String name, int wins, int losses, int draws, int ranking) {
        super.setEntityId(entityId);
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

    public void setTeamColor(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getRanking() {
        return ranking;
    }
}