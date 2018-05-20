package chessgameserver;

import chessgameserver.interfaces.IServerMessageGenerator;
import chessgameserver.interfaces.IServerWebSocket;
import chessgameshared.messages.*;
import model.Tile;

public class ServerMessageGenerator implements IServerMessageGenerator {
    private IServerWebSocket serverWebSocket;

    public ServerMessageGenerator(IServerWebSocket serverWebSocket) {
        this.serverWebSocket = serverWebSocket;
    }

    public void notifyPlayerAdded(String sessionId, String playerName) {
        serverWebSocket.sendToOthers(sessionId, new AnotherPlayerRegisteredMessage(playerName));
    }
    
    public void notifyRegisterResult(String sessionId, boolean succes) {
        serverWebSocket.sendTo(sessionId, new RegistrationResultMessage(succes));
    }

    public void notifyStartGame() {
        serverWebSocket.broadcast(new StartGameMessage());
    }

    public void notifyEndGame() {
        serverWebSocket.broadcast(new EndGameMessage());
    }

    public void notifyUpdateBoard(Tile[][] board){
        serverWebSocket.broadcast(new UpdateBoardMessage(board));
    }
}
