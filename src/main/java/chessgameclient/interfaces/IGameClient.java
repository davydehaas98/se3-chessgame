package chessgameclient.interfaces;

import chessgameshared.interfaces.IClientGUI;
import model.Tile;
import model.enums.Color;

public interface IGameClient {
    void registerClientGUI(IClientGUI ClientGUI);
    void registerPlayer(String name);
    void handleRegistrationResult(boolean succes, Color color);
    void handleAnotherPlayerRegistered(String name);
    void handleRoundStarted();
    void handleGameEnded();
    void handleUpdateBoard(Tile[][] board);
    void makeMove(String from, String to);
    void handleNextTurn(int turn, Color turnColor);
}
