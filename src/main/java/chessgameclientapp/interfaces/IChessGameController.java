package chessgameclientapp.interfaces;

import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;
import model.pieces.Piece;

import java.awt.*;
import java.util.List;

public interface IChessGameController extends IController {
    void setPlayer(Player player);

    void processGameStarted();

    void processGameEnded();

    void processNextTurn(int turn, TeamColor turnTeamColor);

    void processRequestLegalMovesResult(Piece piece, List<Point> legalMoves);

    void processUpdateBoard(Tile[][] board);

    void processEvents(List<Event> events);
}
