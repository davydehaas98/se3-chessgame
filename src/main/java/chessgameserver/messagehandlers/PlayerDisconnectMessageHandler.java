package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.messages.PlayerDisconnectMessage;
import model.interfaces.IGame;

public class PlayerDisconnectMessageHandler extends MessageHandler<PlayerDisconnectMessage> {
    public PlayerDisconnectMessageHandler(IGame game) {
        super(game);
    }

    public void handleMessageInternal(PlayerDisconnectMessage message, String sessionId) {
        getGame().processPlayerDisconnect(message.getSessionId());
    }
}
