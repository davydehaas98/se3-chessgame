package chessgameserver;

import chessgameserver.interfaces.IServerMessageGenerator;
import chessgameserver.interfaces.IServerWebSocket;
import chessgameshared.messages.*;
import model.Tile;
import model.enums.TeamColor;

public class ServerMessageGenerator implements IServerMessageGenerator {
    private IServerWebSocket serverWebSocket;

    public ServerMessageGenerator(IServerWebSocket serverWebSocket) {
        this.serverWebSocket = serverWebSocket;
    }

    public void notifyPlayerAdded(String sessionId, String playerName) {
        serverWebSocket.sendToOthers(sessionId, new AnotherPlayerRegisteredMessage(playerName));
    }

    public void notifyRegistrationResult(String sessionId, TeamColor teamColor) {
        serverWebSocket.sendTo(sessionId, new RegistrationResultMessage(teamColor));
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
}
