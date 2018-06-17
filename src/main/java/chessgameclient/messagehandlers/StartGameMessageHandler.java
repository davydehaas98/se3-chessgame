package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.StartGameMessage;

public class StartGameMessageHandler extends MessageHandler<StartGameMessage> {
    StartGameMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(StartGameMessage message, String sessionId) {
        getGameClient().handleRoundStarted();
    }
}
