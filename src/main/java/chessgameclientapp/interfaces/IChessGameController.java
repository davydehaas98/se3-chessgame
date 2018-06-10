package chessgameclientapp.interfaces;

import model.Event;
import model.Tile;
import model.enums.TeamColor;

public interface IChessGameController {
    void showAlert(String header, String content);
    void processGameStarted();
    void processGameEnded();
    void processNextTurn(int turn, TeamColor turnTeamColor);
    void processUpdateBoard(Tile[][] board);
    void processNewEvent(Event event);
}
