package chessgameshared.interfaces;

import model.Tile;
import model.enums.TeamColor;

public interface IClientGUI {
    void processAnotherPlayerRegistered(String name);
    void processRegistrationResult(TeamColor teamColor);
    void processGameStarted();
    void processGameEnded();
    void processNextTurn(int turn, TeamColor turnTeamColor);
    void processUpdateBoard(Tile[][] board);
}
