package chessgameserver;

import chessgameshared.messages.*;

public class ServerMessageGenerator implements IServerMessageGenerator{
    private IServerWebSocket serverWebSocket;
    public ServerMessageGenerator(IServerWebSocket serverWebSocket){
        this.serverWebSocket = serverWebSocket;
    }

    @Override
    public void notifyRegisterResult(String sessionId, boolean succes) {
        RegistrationResultMessage message = new RegistrationResultMessage(succes);
        serverWebSocket.sendTo(sessionId, message);
    }
    public void notifyPlayerAdded(String sessionId, String playerName) {
        PlayerHasRegisteredMessage message = new PlayerHasRegisteredMessage(playerName);
        serverWebSocket.sendToOthers(sessionId, message);
    }
    public void notifyStartGame(){
        serverWebSocket.broadcast(new StartGameMessage());
    }
}
