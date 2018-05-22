package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.messages.MakeMoveMessage;
import model.interfaces.IGame;

public class MakeMoveMessageHandler extends MessageHandler<MakeMoveMessage> {
    public MakeMoveMessageHandler(IGame game) {
        super(game);
    }

    @Override
    public void handleMessageInternal(MakeMoveMessage message, String sessionId) {
        getGame().makeMove(message.getFrom(), message.getTo());
    }
}
