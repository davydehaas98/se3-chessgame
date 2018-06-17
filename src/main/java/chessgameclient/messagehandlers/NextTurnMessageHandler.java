package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.NextTurnMessage;

public class NextTurnMessageHandler extends MessageHandler<NextTurnMessage> {
    NextTurnMessageHandler(IGameClient igameClient) {
        super(igameClient);
    }

    @Override
    public void handleMessageInternal(NextTurnMessage message, String sessionId) {
        getGameClient().handleNextTurn(message.getTurn(), message.getTurnTeamColor());
    }
}
