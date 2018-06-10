package chessgameserver.interfaces;

import model.Event;
import model.Tile;
import model.enums.TeamColor;

public interface IServerMessageGenerator {
    void notifyRegisterPlayerResult(boolean result, String sessionId);
    void notifyRequestPasswordResult(String password, String sessionId);
    void notifyLoginPlayerResult(TeamColor teamColor, String sessionId);
    void notifyStartGame();
    void notifyEndGame();
    void notifyUpdateBoard(Tile[][] board);
    void notifyNextTurn(int turn, TeamColor turnTeamColor);
    void notifyNewEvent(Event event);
}
