package chessgameshared.interfaces;

import model.Tile;

public interface IClientGUI {
    void processAnotherPlayerRegistered(String name);
    void processRegistrationResponse(boolean succes);
    void processGameStarted();
    void processGameEnded();
    void processNextTurn();
    void processUpdateBoard(Tile[][] board);
}
