package chessgameshared.messages;

import model.enums.TeamColor;

public class NextTurnMessage {
    private int turn;
    private TeamColor turnTeamColor;
    public NextTurnMessage(int turn, TeamColor turnTeamColor) {
        this.turn = turn;
        this.turnTeamColor = turnTeamColor;
    }

    public int getTurn() {
        return turn;
    }

    public TeamColor getTurnTeamColor() {
        return turnTeamColor;
    }
}
