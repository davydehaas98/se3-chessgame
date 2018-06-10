package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.RegisterPlayerResultMessage;

public class RegistrationResultMessageHandler extends MessageHandler<RegisterPlayerResultMessage> {
    public RegistrationResultMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    public void handleMessageInternal(RegisterPlayerResultMessage message, String sessionId) {
        getGameClient().handleRegistrationResult(message.isResult());
    }
}
