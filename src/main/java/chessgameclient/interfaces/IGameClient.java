package chessgameclient.interfaces;

import chessgameclientapp.interfaces.IChessGameController;
import chessgameclientapp.interfaces.ILoginController;
import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;

import java.util.List;

public interface IGameClient {
    void registerChessgameController(IChessGameController chessGameController);

    void registerLoginController(ILoginController loginController);

    void registerPlayer(String name, String password, String confirmPassword);

    void requestPassword(String name, String password);

    void loginPlayer(String name, String password, String passwordToken);

    void makeMove(String from, String to);

    void handleRequestPasswordResult(String password);

    void handleRegistrationResult(boolean result);

    void handleLoginPlayerResult(Player player);

    void handleRoundStarted();

    void handleGameEnded();

    void handleUpdateBoard(Tile[][] board);

    void handleNextTurn(int turn, TeamColor turnTeamColor);

    void handleNewEvent(List<Event> events);
}
