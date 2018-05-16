package chessgameclient.interfaces;

import chessgameshared.interfaces.IClientGUI;

public interface IGameClient {
    void registerClientGUI(IClientGUI ClientGUI);
    void registerPlayer(String name);
    void handlePlayerRegistrationResponse(boolean succes);
    void handlePlayerRegistered(String name);
    void processRoundStarted();
}
