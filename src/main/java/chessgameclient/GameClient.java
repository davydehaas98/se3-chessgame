package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;

public class GameClient implements IGameClient {
    private IClientMessageGenerator messageGenerator;
    private IClientGUI clientGUI;

    public GameClient(IClientMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public void registerClientGUI(IClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    @Override
    public void registerPlayer(String name) {
        messageGenerator.registerPlayerOnServer(name);
    }

    public void handlePlayerRegistrationResponse(boolean success) {
        clientGUI.processRegistrationResponse(success);
    }

    public void handlePlayerRegistered(String name) {
        clientGUI.processPlayerRegistered(name);
    }

    @Override
    public void processRoundStarted() {

    }
}
