package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.messages.RequestPasswordMessage;
import model.interfaces.IGame;

public class RequestPasswordMessageHandler extends MessageHandler<RequestPasswordMessage> {
    RequestPasswordMessageHandler(IGame game) {
        super(game);
    }

    @Override
    public void handleMessageInternal(RequestPasswordMessage message, String sessionId) {
        getGame().requestPassword(message.getName(), sessionId);
    }
}
