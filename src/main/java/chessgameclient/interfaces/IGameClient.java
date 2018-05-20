package chessgameclient.interfaces;

import chessgameshared.interfaces.IClientGUI;
import model.Tile;
import model.pieces.Piece;

public interface IGameClient {
    void registerClientGUI(IClientGUI ClientGUI);
    void registerPlayer(String name);
    void handlePlayerRegistrationResponse(boolean succes);
    void handleAnotherPlayerRegistered(String name);
    void handleRoundStarted();
    void handleGameEnded();
    void handleUpdateBoard(Tile[][] board);
}
