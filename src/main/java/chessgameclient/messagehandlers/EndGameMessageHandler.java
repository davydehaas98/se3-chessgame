package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.EndGameMessage;

public class EndGameMessageHandler extends MessageHandler<EndGameMessage> {
    EndGameMessageHandler(IGameClient igameClient) {
        super(igameClient);
    }

    @Override
    public void handleMessageInternal(EndGameMessage message, String sessionId) {
        getGameClient().handleGameEnded();
    }
}
