package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IClientWebSocket;
import chessgameshared.messages.*;

public class ClientMessageGenerator implements IClientMessageGenerator {
    private IClientWebSocket clientWebSocket;

    public ClientMessageGenerator(IClientWebSocket clientWebSocket) {
        this.clientWebSocket = clientWebSocket;
    }

    public void registerPlayer(String name, String hash) {
        clientWebSocket.onMessageSend(new RegisterPlayerMessage(name, hash));
    }

    public void requestPassword(String name) {
        clientWebSocket.onMessageSend(new RequestPasswordMessage(name));
    }

    public void loginPlayer(String name) {
        clientWebSocket.onMessageSend(new LoginPlayerMessage(name));
    }

    public void makeMove(String from, String to) {
        clientWebSocket.onMessageSend(new MakeMoveMessage(from, to));
    }

    public void playerDisconnect() {
        clientWebSocket.onMessageSend(new PlayerDisconnectMessage());
    }
}
