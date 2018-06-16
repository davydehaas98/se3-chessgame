package chessgameserver.interfaces;

import model.interfaces.IGame;

public interface IServerMessageProcessor {
    void processMessage(String sessionId, String type, String data);

    void handleDisconnect(String sessionId);

    void registerGame(IGame game);
}
