package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.messages.MakeMoveMessage;
import model.interfaces.IGame;

public class MakeMoveMessageHandler extends MessageHandler<MakeMoveMessage> {
    MakeMoveMessageHandler(IGame game) {
        super(game);
    }

    @Override
    public void handleMessageInternal(MakeMoveMessage message, String sessionId) {
        getGame().makeMove(message.getFrom(), message.getTo(), sessionId);
    }
}
