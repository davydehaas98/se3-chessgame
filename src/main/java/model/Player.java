package model;

import model.enums.TeamColor;
import model.interfaces.IPlayer;

public class Player implements IPlayer {
    private String name;
    private String sessionId;
    private TeamColor teamColor;
    private int wins;
    private int losses;
    private int draws;

    Player(String name, String sessionId, TeamColor teamColor, int wins, int losses, int draws) {
        this.name = name;
        this.sessionId = sessionId;
        this.teamColor = teamColor;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    Player(String name, String sessionId, TeamColor teamColor) {
        this.name = name;
        this.sessionId = sessionId;
        this.teamColor = teamColor;
    }

    public String getName() {
        return name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }
}