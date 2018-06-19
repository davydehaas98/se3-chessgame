package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.messages.RequestLegalMovesResultMessage;

public class RequestLegalMovesResultMessageHandler extends MessageHandler<RequestLegalMovesResultMessage> {
    RequestLegalMovesResultMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(RequestLegalMovesResultMessage message, String sessionId) {
        getGameClient().handleRequestLegalMovesResult(message.getPiece(), message.getLegalMoves());
    }
}
