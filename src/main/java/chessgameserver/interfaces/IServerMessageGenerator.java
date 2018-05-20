package chessgameserver.interfaces;

import model.Tile;

public interface IServerMessageGenerator {
    void notifyRegisterResult(String sessionId, boolean succes);
    void notifyPlayerAdded(String sessionId, String name);
    void notifyStartGame();
    void notifyEndGame();
    void notifyUpdateBoard(Tile[][] board);
}
