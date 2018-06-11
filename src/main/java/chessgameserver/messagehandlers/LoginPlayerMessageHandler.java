package chessgameserver.messagehandlers;

import chessgameshared.MessageHandler;
import chessgameshared.messages.LoginPlayerMessage;
import model.interfaces.IGame;

public class LoginPlayerMessageHandler extends MessageHandler<LoginPlayerMessage> {
    LoginPlayerMessageHandler(IGame game){
        super(game);
    }

    public void handleMessageInternal(LoginPlayerMessage message, String sessionId) {
        getGame().loginPlayer(message.getName(), message.getPassword(), sessionId);
    }
}
