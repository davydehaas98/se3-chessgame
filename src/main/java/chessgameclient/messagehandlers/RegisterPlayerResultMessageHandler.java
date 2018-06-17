package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.RegisterPlayerResultMessage;

public class RegisterPlayerResultMessageHandler extends MessageHandler<RegisterPlayerResultMessage> {
    RegisterPlayerResultMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(RegisterPlayerResultMessage message, String sessionId) {
        getGameClient().handleRegisterPlayerResult(message.isResult());
    }
}
