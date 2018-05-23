package chessgameshared.messages;

import model.enums.TeamColor;

public class RegistrationResultMessage {
    private TeamColor teamColor;
    public RegistrationResultMessage(TeamColor teamColor){
        this.teamColor = teamColor;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }
}
