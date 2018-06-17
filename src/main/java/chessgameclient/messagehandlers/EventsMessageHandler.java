package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.EventsMessage;

public class EventsMessageHandler extends MessageHandler<EventsMessage> {
    EventsMessageHandler(IGameClient iGameClient) {
        super(iGameClient);
    }

    @Override
    public void handleMessageInternal(EventsMessage message, String sessionId) {
        getGameClient().handleNewEvent(message.getEvents());
    }
}
