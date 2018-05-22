package chessgameserver.interfaces;

import model.Tile;
import model.enums.Color;

public interface IServerMessageGenerator {
    void notifyRegistrationResult(String sessionId, boolean succes, Color color);
    void notifyPlayerAdded(String sessionId, String name);
    void notifyStartGame();
    void notifyEndGame();
    void notifyUpdateBoard(Tile[][] board);
    void notifyNextTurn(int turn, Color turnColor);
}
