package chessgameserver.interfaces;

import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;

import java.util.List;

public interface IServerMessageGenerator {
    void notifyRegisterPlayerResult(boolean result, String sessionId);

    void notifyRequestPasswordResult(String password, String sessionId);

    void notifyLoginPlayerResult(Player player, String sessionId);

    void notifyStartGame();

    void notifyEndGame();

    void notifyUpdateBoard(Tile[][] board);

    void notifyNextTurn(int turn, TeamColor turnTeamColor);

    void notifyEvents(List<Event> events);
}
