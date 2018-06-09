package chessgameclientapp.interfaces;

import model.Event;
import model.Tile;
import model.enums.TeamColor;

public interface IChessGameController {
    void showAlert(String header, String content);
    void processAnotherPlayerRegistered(String name);
    void processRegistrationResult(TeamColor teamColor);
    void processGameStarted();
    void processGameEnded();
    void processNextTurn(int turn, TeamColor turnTeamColor);
    void processUpdateBoard(Tile[][] board);
    void processNewEvent(Event event);
}
