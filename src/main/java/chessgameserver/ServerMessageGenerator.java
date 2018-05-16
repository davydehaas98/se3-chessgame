package chessgameserver;

import chessgameserver.interfaces.IServerMessageGenerator;
import chessgameserver.interfaces.IServerWebSocket;
import chessgameshared.messages.*;

public class ServerMessageGenerator implements IServerMessageGenerator {
    private IServerWebSocket serverWebSocket;
    public ServerMessageGenerator(IServerWebSocket serverWebSocket){
        this.serverWebSocket = serverWebSocket;
    }

    @Override
    public void notifyRegisterResult(String sessionId, boolean succes) {
        serverWebSocket.sendTo(sessionId, new RegistrationResultMessage(succes));
    }
    public void notifyPlayerAdded(String sessionId, String playerName) {
        serverWebSocket.sendToOthers(sessionId, new PlayerHasRegisteredMessage(playerName));
    }
    public void notifyStartGame(){
        serverWebSocket.broadcast(new StartGameMessage());
    }
}
