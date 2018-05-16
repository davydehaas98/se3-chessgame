package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.RegistrationResultMessage;

public class RegistrationResultMessageHandler extends MessageHandler<RegistrationResultMessage> {
    public RegistrationResultMessageHandler(IGameClient gameClient){
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(RegistrationResultMessage message, String sessionId) {
        getGameClient().handlePlayerRegistrationResponse(message.isResult());
    }
}
