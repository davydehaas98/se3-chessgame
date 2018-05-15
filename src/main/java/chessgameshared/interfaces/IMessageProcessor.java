package chessgameshared.interfaces;

import model.IGame;
import model.enums.MessageType;

public interface IMessageProcessor {
    void processMessage(String sessionId, String type, String data);
    void handleDisconnect(String sessionId);
    void registerGame(IGame game);
}
