package chessgameclient.interfaces;

import chessgameclientapp.interfaces.IChessGameController;
import chessgameclientapp.interfaces.ILoginController;
import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;
import model.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface IGameClient {
    void setChessGameController(IChessGameController chessGameController);

    void setLoginController(ILoginController loginController);

    void registerPlayer(String name, String password, String confirmPassword);

    void requestPassword(String name, String password);

    void loginPlayer(String name, String password, String passwordToken);

    void requestLegalMoves(Piece piece);

    void makeMove(Point from, Point to);

    void handleRequestPasswordResult(String password);

    void handleRegisterPlayerResult(boolean result);

    void handleLoginPlayerResult(Player player);

    void handleRoundStarted();

    void handleGameEnded();

    void handleRequestLegalMovesResult(Piece piece, ArrayList<Point> legalMoves);

    void handleUpdateBoard(Tile[][] board);

    void handleNextTurn(int turn, TeamColor turnTeamColor);

    void handleNewEvent(List<Event> events);
}
