package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IClientWebSocket;
import chessgameshared.messages.RegisterPlayerMessage;

public class ClientMessageGenerator implements IClientMessageGenerator {
    private IClientWebSocket clientWebSocket;
    public  ClientMessageGenerator(IClientWebSocket clientWebSocket){
        this.clientWebSocket = clientWebSocket;
    }
    public  void registerPlayerOnServer(String name){
        clientWebSocket.send(new RegisterPlayerMessage(name));
    }
}
