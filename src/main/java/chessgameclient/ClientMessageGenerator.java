package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IClientWebSocket;
import chessgameshared.messages.MakeMoveMessage;
import chessgameshared.messages.PlayerDisconnectMessage;
import chessgameshared.messages.RegisterPlayerMessage;

public class ClientMessageGenerator implements IClientMessageGenerator {
    private IClientWebSocket clientWebSocket;

    public ClientMessageGenerator(IClientWebSocket clientWebSocket) {
        this.clientWebSocket = clientWebSocket;
    }

    public void registerPlayerOnServer(String name) {
        clientWebSocket.onMessageSend(new RegisterPlayerMessage(name));
    }

    public void makeMove(String from, String to){ clientWebSocket.onMessageSend(new MakeMoveMessage(from, to));}
    public void playerDisconnect() {clientWebSocket.onMessageSend(new PlayerDisconnectMessage());}
}
