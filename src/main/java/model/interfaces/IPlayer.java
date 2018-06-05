package model.interfaces;

import model.enums.TeamColor;

public interface IPlayer {
    String getName();
    TeamColor getTeamColor();
    String getPassword();
    void setTeamColor(TeamColor teamColor);
}
