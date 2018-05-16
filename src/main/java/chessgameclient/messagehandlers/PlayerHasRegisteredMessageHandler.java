package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.PlayerHasRegisteredMessage;

public class PlayerHasRegisteredMessageHandler extends MessageHandler<PlayerHasRegisteredMessage> {
    public PlayerHasRegisteredMessageHandler(IGameClient gameClient){
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(PlayerHasRegisteredMessage message, String sessionId) {
        getGameClient().handlePlayerRegistered(message.getName());
    }
}
