package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.messages.LogoutPlayerMessage;
import model.interfaces.IGame;

public class LogoutPlayerMessageHandler extends MessageHandler<LogoutPlayerMessage> {
    LogoutPlayerMessageHandler(IGame game) {
        super(game);
    }

    @Override
    public void handleMessageInternal(LogoutPlayerMessage message, String sessionId) {
        getGame().logoutPlayer(sessionId);
    }
}
