package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.RequestPasswordResultMessage;

public class RequestPasswordResultMessageHandler extends MessageHandler<RequestPasswordResultMessage> {
    public RequestPasswordResultMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    public void handleMessageInternal(RequestPasswordResultMessage message, String sessionId) {
        getGameClient().handleRequestPasswordResult(message.getPassword());
    }
}
