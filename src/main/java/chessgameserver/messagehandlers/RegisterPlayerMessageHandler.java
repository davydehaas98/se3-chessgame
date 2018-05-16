package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.messages.RegisterPlayerMessage;
import model.interfaces.IGame;

public class RegisterPlayerMessageHandler extends MessageHandler<RegisterPlayerMessage> {
    public RegisterPlayerMessageHandler(IGame game){
        super(game);
    }

    @Override
    public void handleMessageInternal(RegisterPlayerMessage message, String sessionId) {
        getGame().registerNewPlayer(message.getName(), sessionId);
    }
}
