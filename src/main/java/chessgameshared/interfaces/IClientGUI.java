package chessgameshared.interfaces;

import model.Tile;
import model.enums.Color;

public interface IClientGUI {
    void processAnotherPlayerRegistered(String name);
    void processRegistrationResult(boolean succes, Color color);
    void processGameStarted();
    void processGameEnded();
    void processNextTurn(int turn, Color turnColor);
    void processUpdateBoard(Tile[][] board);
}
