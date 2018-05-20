package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.AnotherPlayerRegisteredMessage;

public class AnotherPlayerRegisteredMessageHandler extends MessageHandler<AnotherPlayerRegisteredMessage> {
    public AnotherPlayerRegisteredMessageHandler(IGameClient gameClient){
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(AnotherPlayerRegisteredMessage message, String sessionId) {
        getGameClient().handleAnotherPlayerRegistered(message.getName());
    }
}
