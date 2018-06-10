package chessgameclient.interfaces;

import chessgameclientapp.interfaces.IChessGameController;
import chessgameclientapp.interfaces.ILoginController;
import model.Event;
import model.Tile;
import model.enums.TeamColor;

public interface IGameClient {
    void registerChessgameController(IChessGameController chessGameController);
    void registerLoginController(ILoginController loginController);
    void requestPassword(String name, String password);
    void registerPlayer(String name, String password, String confirmPassword);
    void makeMove(String from, String to);
    void handleRequestPasswordResult(String password);
    void handleRegistrationResult(boolean result);
    void handleLoginPlayerResult(TeamColor teamColor);
    void handleRoundStarted();
    void handleGameEnded();
    void handleUpdateBoard(Tile[][] board);
    void handleNextTurn(int turn, TeamColor turnTeamColor);
    void handleNewEvent(Event event);
    void loginPlayer(String name, String password, String passwordToken);
}
