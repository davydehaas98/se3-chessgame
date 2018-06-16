package chessgameshared.interfaces;

import model.interfaces.IGame;

public interface IMessageProcessor {
    void processMessage(String sessionId, String type, String data);

    void handleDisconnect(String sessionId);

    void registerGame(IGame game);
}
