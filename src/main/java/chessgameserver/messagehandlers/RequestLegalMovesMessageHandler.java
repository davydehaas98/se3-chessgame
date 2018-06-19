package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.messages.RequestLegalMovesMessage;
import model.interfaces.IGame;

public class RequestLegalMovesMessageHandler extends MessageHandler<RequestLegalMovesMessage> {
    RequestLegalMovesMessageHandler(IGame game) {
        super(game);
    }

    @Override
    public void handleMessageInternal(RequestLegalMovesMessage message, String sessionId) {
        getGame().requestLegalMoves(message.getPiece(), sessionId);
    }
}
