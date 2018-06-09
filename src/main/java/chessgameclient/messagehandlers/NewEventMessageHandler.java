package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.NewEventMessage;

public class NewEventMessageHandler extends MessageHandler<NewEventMessage> {
    public NewEventMessageHandler(IGameClient iGameClient){
        super(iGameClient);
    }
    @Override
    public void handleMessageInternal(NewEventMessage message, String sessionId) {
        getGameClient().handleNewEvent(message.getEvent());
    }
}
