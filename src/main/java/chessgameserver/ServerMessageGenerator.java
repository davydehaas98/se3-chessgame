package chessgameserver;

import chessgameserver.interfaces.IServerMessageGenerator;
import chessgameserver.interfaces.IServerWebSocket;
import chessgameshared.messages.*;
import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;

import java.util.List;

public class ServerMessageGenerator implements IServerMessageGenerator {
    private IServerWebSocket serverWebSocket;

    public ServerMessageGenerator(IServerWebSocket serverWebSocket) {
        this.serverWebSocket = serverWebSocket;
    }

    public void notifyRegisterPlayerResult(boolean result, String sessionId) {
        serverWebSocket.sendTo(sessionId, new RegisterPlayerResultMessage(result));
    }

    public void notifyRequestPasswordResult(String password, String sessionId) {
        serverWebSocket.sendTo(sessionId, new RequestPasswordResultMessage(password));
    }

    public void notifyLoginPlayerResult(Player player, String sessionId) {
        serverWebSocket.sendTo(sessionId, new LoginPlayerResultMessage(player));
    }

    public void notifyStartGame() {
        serverWebSocket.broadcast(new StartGameMessage());
    }

    public void notifyEndGame() {
        serverWebSocket.broadcast(new EndGameMessage());
    }

    public void notifyUpdateBoard(Tile[][] board) {
        serverWebSocket.broadcast(new UpdateBoardMessage(board));
    }

    public void notifyNextTurn(int turn, TeamColor turnTeamColor) {
        serverWebSocket.broadcast(new NextTurnMessage(turn, turnTeamColor));
    }

    public void notifyEvents(List<Event> events) {
        serverWebSocket.broadcast(new EventsMessage(events));
    }
}
