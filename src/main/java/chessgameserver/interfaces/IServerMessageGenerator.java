package chessgameserver.interfaces;

import model.Event;
import model.Tile;
import model.enums.TeamColor;

public interface IServerMessageGenerator {
    void notifyRegisterResult(boolean result, String sessionId);
    void notifyRequestPassword(String password, String sessionId);
    void notifyLoginResult(TeamColor teamColor, String sessionId);
    void notifyStartGame();
    void notifyEndGame();
    void notifyUpdateBoard(Tile[][] board);
    void notifyNextTurn(int turn, TeamColor turnTeamColor);
    void notifyNewEvent(Event event);
}
