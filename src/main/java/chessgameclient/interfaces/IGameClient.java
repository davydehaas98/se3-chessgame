package chessgameclient.interfaces;

import chessgameshared.interfaces.IClientGUI;
import model.Tile;
import model.enums.TeamColor;

public interface IGameClient {
    void registerClientGUI(IClientGUI ClientGUI);
    void registerPlayer(String name);
    void handleRegistrationResult(TeamColor teamColor);
    void handleAnotherPlayerRegistered(String name);
    void handleRoundStarted();
    void handleGameEnded();
    void handleUpdateBoard(Tile[][] board);
    void makeMove(String from, String to);
    void handleNextTurn(int turn, TeamColor turnTeamColor);
}
