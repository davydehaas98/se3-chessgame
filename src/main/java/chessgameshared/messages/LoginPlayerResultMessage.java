package chessgameshared.messages;

import model.enums.TeamColor;

public class LoginPlayerResultMessage {
    private TeamColor teamColor;

    public LoginPlayerResultMessage(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }
}
