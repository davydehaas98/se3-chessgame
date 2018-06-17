package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.LoginPlayerResultMessage;

public class LoginPlayerResultMessageHandler extends MessageHandler<LoginPlayerResultMessage> {
    LoginPlayerResultMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(LoginPlayerResultMessage message, String sessionId) {
        getGameClient().handleLoginPlayerResult(message.getPlayer());
    }
}
