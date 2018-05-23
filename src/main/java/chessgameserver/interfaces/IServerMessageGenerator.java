package chessgameserver.interfaces;

import model.Tile;
import model.enums.TeamColor;

public interface IServerMessageGenerator {
    void notifyRegistrationResult(String sessionId, TeamColor teamColor);
    void notifyPlayerAdded(String sessionId, String name);
    void notifyStartGame();
    void notifyEndGame();
    void notifyUpdateBoard(Tile[][] board);
    void notifyNextTurn(int turn, TeamColor turnTeamColor);
}
