package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;

public class GameClient implements IGameClient {
    IClientMessageGenerator messageGenerator;
    private IClientGUI clientGUI;
    public GameClient(IClientMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
    }
    public void registerClientGUI(IClientGUI clientGUI){
        this.clientGUI = clientGUI;
    }

    @Override
    public void registerPlayer(String name) {
        messageGenerator.registerPlayerOnServer(name);
    }
}
