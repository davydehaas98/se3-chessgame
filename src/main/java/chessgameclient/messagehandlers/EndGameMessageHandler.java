package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.messages.EndGameMessage;
import sun.plugin2.message.Message;

public class EndGameMessageHandler extends MessageHandler<EndGameMessage> {
    public EndGameMessageHandler(IGameClient igameClient) {
        super(igameClient);
    }

    @Override
    public void handleMessageInternal(EndGameMessage message, String sessionId) {
        getGameClient().handleGameEnded();
    }
}
