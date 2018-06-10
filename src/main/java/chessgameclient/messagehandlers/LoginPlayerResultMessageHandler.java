package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.messages.LoginPlayerResultMessage;

public class LoginPlayerResultMessageHandler extends MessageHandler<LoginPlayerResultMessage> {
    public LoginPlayerResultMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    public void handleMessageInternal(LoginPlayerResultMessage message, String sessionId) {
        getGameClient().handleLoginPlayerResult(message.getTeamColor());
    }
}
